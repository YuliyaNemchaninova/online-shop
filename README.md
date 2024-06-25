# Часть 0 Запуск minikube

minikube delete

minikube start

minikube start --cpus 4 --memory 8192 --disk-size 40000

minikube addons enable ingress

minikube ip

Прописать <ip> arch.homework в системном файле etc/hosts

# Часть 1 Запуск PosgtreSQL

cd <путь в папку postgresql>

helm repo update

kubectl apply -f local-pv.yaml -f pv-claim.yaml

helm install postgresql-dev -f values.yaml bitnami/postgresql

# Часть 2 Установить сервисы

**person**

cd <путь в папку person-deployment>

kubectl apply -f config-map.yaml -f secret.yaml -f dp.yaml -f service.yaml -f serviceMonitor.yaml

**inventory**

cd <путь в папку inventory-deployment>

kubectl apply -f inventory-config-map.yaml -f inventory-secret.yaml -f dp.yaml -f service.yaml

**billing**

cd <путь в папку billing-deployment>

kubectl apply -f billing-config-map.yaml -f billing-secret.yaml -f dp.yaml -f service.yaml

**order**

cd <путь в папку order-deployment>

kubectl apply -f order-config-map.yaml -f order-secret.yaml -f dp.yaml -f service.yaml -f serviceMonitor.yaml

**auth** 

Собираем и запускаем с помощью **skaffold** сервис аутентификации

cd <путь в папку auth>

skaffold run

kubectl apply -f auth-ingress.yaml

**ingress**

cd <minikube-deployment>

kubectl apply -f ingress.yaml

# Часть 3 У**становить prometheus и Grafana**

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm repo add stable https://charts.helm.sh/stable 

helm repo update

helm install prometheus prometheus-community/kube-prometheus-stack

kubectl port-forward deployment/prometheus-grafana 3000

После этого UI Графана доступен по адресу http://localhost:3000

Логин admin 

Пароль prom-operator

# Часть 3 Тестирование

**Проверка приложения**

kubectl get pods
kubectl get ingress

В другом окне Командной строки

minikube tunnel

**Выполнить тесты** 

1) Аутентификация

newman run gateway.postman_collection.json --env-var "url=http://arch.homework”

2) Создание заказа

newman run Order.postman_collection.json --env-var "url=http://arch.homework” --env-var "inventoryUrl=http://arch.homework” --env-var "billingUrl=http://arch.homework”
