#include <stdio.h>
#include <stdlib.h>
int coordX(void)
{
int l;
do{
    printf("Indique a coordenada das abcissas onde pretende inserir o Quadrado: ");
    scanf("%d",&l);
}while (l<=0 || l >= 86);
return l;
}

int coordY(void)
{
int l;
do{
    printf("Indique a coordenada das ordenadas onde pretende inserir o Quadrado: ");
    scanf("%d",&l);
}while (l<=0 || l >= 25);
return l;
}

int comprimento(void)
{
int l;
do{
    printf("Indique o comprimento do retângulo: ");
    scanf("%d",&l);
}while (l<=0);
return l;
}
 
 
int altura(void)
{
int a;
do{
    printf("Indique a altura do retângulo: ");
    scanf("%d",&a);
}while (a<=0);
return a;
}
 

void cria_tabuleiro(int x, int comp, int y, int high)
{

int linha,coluna;
   for(linha=1;linha<=25;linha++){
       for(coluna=1;coluna<=85;coluna++)
       if(linha==1 || linha==25 || coluna==1 || coluna==85)
          printf("-");
        else if((linha >= x && linha <= (x + comp-1)) && (coluna >= y && coluna <= (y + high-1)))
        {
          printf("+");
        }
       else
          printf(" ");
          printf("\n");
   }

   printf("OLA\n");
}

void exibeMatriz(int matriz[][]){


    for (int x = 0; x <= 85; x++){
        for (int y = 0; y <= 25; y++){
            matriz[x][y] = 0;
        }


    for (int x = 0; x <= 85; x++){
        for (int y = 0; y <= 25; y++){
            printf("%d ", matriz[x][y]);
        }
        printf("\n");
    }

}


int main ()
{ 
   int x,y,comp,alt;


   char matriz[85][25];
   exibeMatriz(matriz[85][25]);

    //x=coordX();
    //y=coordY();
    //comp=comprimento();
    //alt=altura();
    //cria_tabuleiro(,x,alt,y,comp);
    getchar();

    return 0;
}
 
