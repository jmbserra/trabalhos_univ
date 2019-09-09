%% pintar(?Ncoroas, ?Msectores, ?Kcores, ?Disco)
%
% Cria um disco com N coroas e M sectores e pinta-o
% com K cores de acordo com as regras descritas no enunciado
pintar(Ncoroas, Msectores, Kcores, Disco):-
  inicializar(Msectores, Ncoroas, Disco),
  pinta(Disco, Kcores),
  Disco = [Si|_],
  last(Disco, Sf), %obtem o ultimo sector do disco
  compara_sectores(Si, Sf), % compara o primeiro e ultimo sectores do disco
  configs_diferentes(Disco), % verifica se nenhuma configuração se repete
  write("Disco: \n"), write(Disco), write("\n ").

%% inicializar(?Nsectores, ?Ncoroas, ?Disco)
%
% Inicializa a lista de sectores (listas) do disco
inicializar(Nsectores, Ncoroas, Disco) :-
  length(Disco, Nsectores),  % cria lista de aridade N (disco com N sectores)
  maplist(cria_sector(Ncoroas), Disco). % cria lista para cada sector do disco

%%cria_sector(+Ncoroas,?Sector)
%
% Cria uma lista de coroas (sector) com N elementos não instanciados
cria_sector(Ncoroas, Sector) :-
  length(Sector, Ncoroas).

%%compara_sectores(+Sector1, +Sector2)
%
% Verifica se 2 sectores diferem apenas na cor de uma das suas coroas
compara_sectores([C|Cs1], [C|Cs2]) :- compara_sectores(Cs1, Cs2).
compara_sectores([C1|Cs1], [C2|Cs1]) :- C1 \= C2.

%%configs_diferentes(?Disco)
%
% Todas as configurações no disco são únicas se pegar numa
% configuração e não a encontrar na lista das restantes configurações
%
% (Todos os elementos da lista são diferentes se pegar num elemento da lista
% e não o encontrar na lista dos restantes elementos )
configs_diferentes(Disco) :-
  \+ (select(Sector,Disco,Restantes), memberchk(Sector,Restantes)).


%%pinta(+Disco, +Cores)
%
% Instancia as coroas de cada sector com uma cor e compara os sectores
pinta([S1, S2], Cores):- % caso em que restam 2 sectores por pintar
  pinta_sector(S1, Cores), pinta_sector(S2, Cores),
  compara_sectores(S1, S2).
pinta([S1, S2, S3], Cores):- % caso em que restam 3 sectores por pintar
  pinta_sector(S1, Cores), pinta_sector(S2, Cores), pinta_sector(S3, Cores),
  compara_sectores(S1, S2), compara_sectores(S2, S3).
pinta([S1,S2|Ss], Cores):-
  pinta_sector(S1, Cores), pinta_sector(S2, Cores),
  compara_sectores(S1, S2), pinta(Ss, Cores).

%%pinta_sector(+Sector, +Cores)
%
% Instancia cada coroa do sector com uma cor
pinta_sector([], _).
pinta_sector([C|Cs], Cores):-
  pinta_coroa(C, Cores),  pinta_sector(Cs, Cores).

%%pinta_sector(+Sector, +Cores)
%
% Pinta a coroa instanciado a coroa com uma cor
pinta_coroa(Coroa, Cores):-
  member(Coroa, Cores).
