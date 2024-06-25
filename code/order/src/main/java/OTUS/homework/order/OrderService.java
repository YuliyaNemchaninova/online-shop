package OTUS.homework.order;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.net.http.HttpClient.newHttpClient;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Value("${api.url.book.item}")
    private String bookItemUrl;
    @Value("${api.url.cancel.booking}")
    private String cancelBookUrl;
    @Value("${api.url.create.payment}")
    private String createPaymentUrl;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();

    }


public HttpResponse<String> bookItem(InventoryRq inventoryRq) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(inventoryRq);
        System.out.println("Адрес " + bookItemUrl + " Запрос " + jsonRequest);

    HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(new URI(bookItemUrl))
            .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
            .setHeader("Content-Type","application/json")
            .build();

    HttpClient httpClient = newHttpClient();
    HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
    System.out.println("Ответ " + postResponse.body());
    return postResponse;
    }

    public HttpResponse<String> cancelItemBooking(InventoryRq inventoryRq) throws URISyntaxException, IOException, InterruptedException {

        Gson gson = new Gson();

        String jsonRequest = gson.toJson(inventoryRq);
        System.out.println("Адрес " + cancelBookUrl + " Запрос " + jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(cancelBookUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .setHeader("Content-Type","application/json")
                .build();

        HttpClient httpClient = newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Ответ " + postResponse.body());
        return postResponse;
    }


    public HttpResponse<String> createPayment(Payment payment) throws URISyntaxException, IOException, InterruptedException {

        Gson gson = new Gson();

        String jsonRequest = gson.toJson(payment);
        System.out.println("Адрес " + createPaymentUrl + " Запрос " + jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(createPaymentUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .setHeader("Content-Type","application/json")
                .build();

        HttpClient httpClient = newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Ответ " + postResponse.body());
        return postResponse;
    }


    public Order addNew(Order order) throws URISyntaxException, IOException, InterruptedException {
        boolean exists = orderRepository.existsById(order.getOrderId());
        if (!exists) {
            order.setStatus("NEW");
            Order savedOrder = orderRepository.save(order);

            // Начало саги
            // Забронировать товар
            InventoryRq inventoryRq = new InventoryRq(order.getItemId(), order.getOrderQuantity());
            HttpResponse<String> bookResponse =  bookItem(inventoryRq);

            if (bookResponse.statusCode() == 200) {

                // Списать деньги
                Payment payment = new Payment();
                payment.setClientId(order.getClientId());
                payment.setSum(order.getTotalSum());

                HttpResponse<String> paymentResponse =  createPayment(payment);


                if (paymentResponse.statusCode() == 200) {
                    order.setStatus("OK");
                    savedOrder = orderRepository.save(order);
                }
                else {
                    order.setStatus("PAYMENT ERROR");
                    savedOrder = orderRepository.save(order);

                    //Отменить бронь товара
                    HttpResponse<String> cancelItemBookingResponse = cancelItemBooking(inventoryRq);

                    //Конец саги
                    throw new IllegalStateException("Ошибка оплаты");
                }

            }
            else {
                order.setStatus("BOOKING ERROR");
                savedOrder = orderRepository.save(order);
                throw new IllegalStateException("Ошибка бронирования товара");
            }

            return savedOrder;
        } else return orderRepository.findById(order.getOrderId()).orElseThrow(() ->
                new IllegalStateException("Заказ уже существует. Неудалось получить найденный заказ"));


    }
}


