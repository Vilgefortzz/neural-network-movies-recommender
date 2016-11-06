**MOVIES RECOMMENDER**
---------------------------------------------------------------------------------------------------------------------

_Autor: Grzegorz Klimek Gr. Proj. 2 rok III IS WIMIiP_
------------------------------------------------------

Opis projektu: 

Projekt ten przewiduje stworzenie aplikacji, której zadaniem będzie
polecanie filmów użytkownikowi na podstawie jego wyborów, wyborów innych
użytkowników, ocen, gatunków. Program będzie się uczył nowych zachowań 
i polecał jak najtrafniej filmy danemu użytkownikowi.

---------------------------------------

**_Harmonogram:_**

[1] Zdefiniowanie problemu do rozwiązania, implementacja neuronu McCullocha-Pitts'a, 
    jednowarstwowej sieci neuronowej-perceptronu. Wykorzystanie go do nauki
    podstawowych bramek logicznych ( AND, OR, NOT ) oraz przygotowanie zestawu danych uczących.
    
    Podproblem do rozwiązania: Wybranie pięciu filmów o jakiejś ocenie w skali od 0-10 
    a następnie na podstawie oceny jakiegoś filmu przez użytkownika sklasyfikowanie filmów 
    o mniejszej ocenie i większej. Filmy, które można mu polecić a których nie można.
    Do uczenia zostanie wykorzystany perceptron. Do modyfikowania wag wykorzystana zostanie
    reguła Widrowa – Hoffa (reguła delta)

[2] Implementacja sieci wielowarstwowych - współpracujące ze sobą neurony i wzajemnie się 
    komunikujące. Praca nad doskonaleniem polecania filmów
    
[3] Uczenie sieci jednowarstwowej przy użyciu reguły Widrowa - Hoffa dla wybranego podproblemu

[4] Praca nad siecią neuronową i neuronami wchodzącymi w jej skład - poprawienie metody uczenia itp.

[5] Uczenie sieci z wykorzystaniem sieci wielowarstwowej dla wybranego pod-problemu

---------------------------------------------------------------------------------------------------------------------