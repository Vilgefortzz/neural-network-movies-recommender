#include <iostream>
#include <stdlib.h>
#include <time.h>
#define randomize() srand((unsigned)time(NULL))
#define POP_SIZE 20
#define CHROM_SIZE 10
int pop[POP_SIZE][CHROM_SIZE]; // tablica osobników
int temp[POP_SIZE][CHROM_SIZE]; // pomocnicza tablica osobników

using namespace std;

void init(int t[], int n)
{
	for (int i = 0; i<n; i++) t[i] = rand() % 2; // inicjalizacja losowa: 0 lub 1
}
void mutation(int t[], int n)
{
	int position = rand() % n; // losowy gen osobnika
	t[position] = 1 - t[position]; // mutacja: 0 na 1 lub 1 na 0
}
void crossover(int t1[], int t2[], int n)
{
	int position = rand() % (n - 1) + 1;
	for (int i = position; i<n; i++)
	{ // od losowego miejsca
		int p = t1[i]; // zamieniamy miejscami geny osobników
		t1[i] = t2[i];
		t2[i] = p;
	}
}
double fitness(int t[], int n) // funkcja przystosowania
{
	double s = 0;
	for (int i = 0; i<n; i++) s += t[i];
	s /= n; // przyk³ad: zmaksymalizowaæ kwadrat
	return s*s; // œredniej wartoœci genów
}
void copy(int dest[], int source[], int n)
{
	for (int i = 0; i<n; i++) dest[i] = source[i];
}
void print(int t[], int n) // funkcja pomocnicza: wypisz geny osobnika
{
	for (int i = 0; i<n; i++) cout << t[i] << " ";
	cout << " = " << fitness(t, n) << endl; //...i wartoœæ jego funkcji celu
}
int flip(double p) // pomocnicza: zwraca 1 z prawdopodobieñstwem p
{
	return p<rand() / (double)(RAND_MAX + 1);
}void RouletteWheel(double fitness[], int result[], int n, int m = -1)
{
	double *sum = new double[n]; // dystrybuanta rozk³adu: kolejne sumy
	if (m == -1) m = n; // domyœlnie: m = n
	sum[0] = (fitness[0]>0 ? fitness[0] : 0);
	for (int i = 1; i<n; i++) sum[i] = sum[i - 1] + (fitness[i]>0 ? fitness[i] : 0);
	if (sum[n - 1]>0) // sum[n-1] przechowuje sumê wartoœci ca³ej populacji
	{
		for (int i = 0; i<n; i++) sum[i] /= sum[n - 1]; // normalizujemy rozk³ad do sumy 1
		for (int i = 0; i<m; i++)
		{
			double r = rand() / (double)(RAND_MAX + 1); // r - losowe z przedzia³u [0,1)
			int j;
			for (j = 0; sum[j]<r; j++);
			result[i] = j; // j - numer pierwszego elementu tablicy sum
						   // wiêkszego lub równego r
		};
	}
	else for (int i = 0; i<m; i++) result[i] = 0;
	delete[] sum;
}void main()
{
	randomize();
	// inicjacja losowa osobników:
	for (int i = 0; i<POP_SIZE; i++) init(pop[i], CHROM_SIZE);
	cout << "Wynik - populacja 0" << endl;
	for (int i = 0; i<POP_SIZE; i++) print(pop[i], CHROM_SIZE);
	const double mutation_prob = 0.1; // prawdopodobieñstwo mutacji osobnika
	const double crossover_prob = 0.5; // prawdopodobieñstwo krzy¿owania pary
	for (int t = 0; t<100; t++) // ograniczenie pêtli - liczba pokoleñ
	{
		for (int i = 0; i<POP_SIZE; i++) // operatory genetyczne
		{
			if (flip(mutation_prob)) mutation(pop[i], CHROM_SIZE);
			if (flip(crossover_prob))
			{
				int second = rand() % POP_SIZE; // losowy wybór partnera
				crossover(pop[i], pop[second], CHROM_SIZE);
			};
		};
		double f[POP_SIZE]; // wartoœci funkcji przystosowania osobników
		int selected[POP_SIZE]; // numery osobników wybranych "ko³em ruletki"
		for (int i = 0; i<POP_SIZE; i++) f[i] = fitness(pop[i], CHROM_SIZE);
		RouletteWheel(f, selected, POP_SIZE);
		for (int i = 0; i<POP_SIZE; i++) copy(temp[i], pop[selected[i]], CHROM_SIZE);
		// kopiowanie wybranych osobników do tablicy pomocniczej
		for (int i = 0; i<POP_SIZE; i++) copy(pop[i], temp[i], CHROM_SIZE);
		// kopiowanie zawartoœci tablicy pomocniczej do podstawowej
		
	};
	cout << "Wynik - populacja koñcowa:" << endl;
	for (int i = 0; i<POP_SIZE; i++) print(pop[i], CHROM_SIZE);
	system("pause");
}