# Gines

## Gines is not epidemic simulation

#### Zależności:
* Scala >= 2.10
* sbt >= 0.13.*
* ZeroMQ == 2.*
* reszta zależności zostanie ściągnięta przez sbt w czasie kompilacji.

Program składa się z dwóch części: serwera i GUI.

GUI uruchamia się wywołując polecenia:
* sbt będąc w folderze gines-gui,
* run, w sbt.

Po uruchomieniu, dostępna jest strona internetowa pod adresem
127.0.0.1:9000 przedstawiająca symulację.

Aby GUI działało, należy uruchomić serwer symulacji znajdujący się w
pliku .jar - poleceniem

    java -jar gines.jar application.conf

Application.conf to plik zawierający opcje konfiguracyjne symulacji - wzór
znajduje się w folderze src/main/resources. Jeśli nie zostanie podany,
zostanie wczytana konfiguracja zawarta w pliku .jar.


Z działającym serwerem symulacji strona jest w pełni funkcjonalna
i umożliwia sterowanie przebiegiem symulacji. Dodatkowo, wyświetlany
jest aktualny stan świata symulacji i wykres ilości osób
chorych/zdrowych/odpornych.
