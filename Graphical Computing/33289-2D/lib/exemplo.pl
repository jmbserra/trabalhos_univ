









h(obstruida(X,Y),r(mover(X,Y),S)):-
				h(esta(W,Z),S).



h(visitada(X,Y),r(_,S)):-
	h(esta(X,Y),S),
	h(visitada(X,Y),S).




h(ganhou(), r(_,S)):-
	h(ganhou(),S).




h(perdeu(), r(_,S)):-
	h(perdeu(),S).