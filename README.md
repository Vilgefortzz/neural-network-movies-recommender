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
    reguły delta ( Widrowa-Hoffa ) - uczenie z nauczycielem.

[2] ~~Implementacja sieci wielowarstwowych - współpracujące ze sobą neurony, wzajemnie się 
    komunikujące. Praca nad doskonaleniem polecania filmów~~ [Zrealizowane 14.11.16]
    
    Podproblem do rozwiązania: Problem bramki XOR. Implementacja sieci neuronowej
    trójwarstwowej ( input, hidden, output ) w celu rozwiązania tego problemu. Testowanie sieci
    aby dobrać jak najlepsze parametry uczenia. Wykorzystanie algorytmu wstecznej propagacji do 
    modyfikowania błędów neuronów pracujących w warstwach oraz do modyfikowania wag. Uczenie regułą
    delta - ADALINE.
    
[3] ~~Doskonalenie sieci neuronowej - praca nad dalszą poprawą polecania filmów~~ [Zrealizowane 21.11.16]
    
[4] ~~Praca nad wielowarstwową siecią neuronową i neuronami wchodzącymi w jej skład,
    poprawienie metody uczenia itp.~~ [Zrealizowane 28.11.16]

[5] ~~Uczenie regułą Hebba - bez nauczyciela~~ [Zrealizowane 28.11.16] 

    Podproblem do rozwiązania: Dopasowanie wzorca oraz grupowanie danych dla zestawu filmów.
    Grupowanie będzie się odbywało na zasadzie takiej, że za pomocą reguły Hebba, reguły Oji oraz
    reguły Hebba z wspołczynnikiem zapominania ( przy wybraniu jednej z nich aczkolwiek każda z nich
    będzie zaimplementowana ) filmy będą klasyfikowane na podstawie ich rankingu od 1-10 a następnie 
    będą trafiały do danej grupy np. dobrych filmów, przeciętnych lub tych bardzo dobrych.

[6] ~~Sieci Kohonena. Wykorzystanie uczenia regułą WTA ( winner takes all )~~ [Zrealizowane 05.12.16]

    Podproblem do rozwiązania: Tym razem będę zajmował się klasyfikacją kolorów.
    Dzięki temu będę mógł wizualnie zobaczyć proces klasyfikowania kolorów na
    mapach 2D i wykorzystać to do rozwiązywania innych podproblemów.

[7] ~~Dalsza praca nad sieciami Kohonena - SOMY. Wykorzystanie uczenia regułą WTM ( winner takes most )~~ [Zrealizowane 19.12.16]

    Podproblem do rozwiązania: Wykorzystany zostanie problem z poprzedniego punktu harmonogramu
    czyli klasyfikacja kolorów. Tym razem w celu rozwiązania tego problemu zostanie wykorzystany
    algorytm WTM.

---