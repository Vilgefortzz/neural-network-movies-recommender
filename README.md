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

[1] ~~Zdefiniowanie problemu do rozwiązania, implementacja neuronu McCullocha-Pitts'a, 
    jednowarstwowej sieci neuronowej-perceptronu. Wykorzystanie go do nauki
    podstawowych bramek logicznych ( AND, OR, NOT ) oraz przygotowanie zestawu danych uczących.~~ [Zrealizowane 07.11.16]
    
    Podproblem do rozwiązania: Wybranie pięciu filmów o jakiejś ocenie w skali od 0-10 
    a następnie na podstawie oceny jakiegoś filmu przez użytkownika sklasyfikowanie filmów 
    o mniejszej ocenie i większej. Filmy, które można mu polecić a których nie można.
    Do uczenia zostanie wykorzystany perceptron i metoda perceptronowa. Całość uczona jest za pomocą
    reguły delta ( Widrowa-Hoffa ) - uczenie z nauczycielem

[2] ~~Implementacja sieci wielowarstwowych - współpracujące ze sobą neurony, wzajemnie się 
    komunikujące. Praca nad doskonaleniem polecania filmów~~ [Zrealizowane 14.11.16]
    
    Podproblem do rozwiązania: Problem bramki XOR. Implementacja sieci neuronowej
    trójwarstwowej ( input, hidden, output ) w celu rozwiązania tego problemu. Testowanie sieci
    aby dobrać jak najlepsze parametry uczenia. Wykorzystanie algorytmu wstecznej propagacji do 
    modyfikowania błędów neuronów pracujących w warstwach oraz do modyfikowania wag. Uczenie regułą
    delta - ADALINE
    
[3] Doskonalenie sieci neuronowej - praca nad dalszą poprawą polecania filmów
    
[4] Uczenie sieci jednowarstwowej przy użyciu reguły Widrowa - Hoffa dla wybranego podproblemu

[5] Praca nad siecią neuronową i neuronami wchodzącymi w jej skład - poprawienie metody uczenia itp.

[6] Uczenie sieci z wykorzystaniem sieci wielowarstwowej dla wybranego pod-problemu

[7] Uczenie regułą Hebba - bez nauczyciela

[8] Wykorzystanie uczenia regułą Hebba do rozwiązywania wybranych pod-problemów

---------------------------------------------------------------------------------------------------------------------