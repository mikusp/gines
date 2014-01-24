Gines
=====

Gines is not epidemic simulation


Program składa się z dwóch części: serwera i GUI.

GUI uruchamia się wywołując polecenia:
    - sbt będąc w folderze gines-gui,
    - run, w sbt.

Po uruchomieniu, dostępna jest strona internetowa pod adresem
127.0.0.1:9000 przedstawiająca symulację.

Aby GUI działało, należy uruchomić serwer symulacji znajdujący się w
pliku .jar - poleceniem

    java -jar gines.jar konfig.conf

Konfig.conf to plik zawierający opcje konfiguracyjne symulacji.


Z działającym serwerem symulacji strona jest w pełni funkcjonalna
i umożliwia sterowanie przebiegiem symulacji. Dodatkowo, wyświetlany
jest aktualny stan świata symulacji i wykres ilości osób
chorych/zdrowych/odpornych.
