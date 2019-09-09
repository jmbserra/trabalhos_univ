package expressoes;

import java.util.Scanner;

public class Expressoes{
	public static DoublyLinkedList<Integer> ops = new DoublyLinkedList<Integer>();// DLL operandos
	public static DoublyLinkedList<String> exp = new DoublyLinkedList<String>(); // Expressao final Postfix

	// alinea 1.
	public static ExpBinTree postfix(String infix) throws Exception{
		String output;
		InfixToPostfix converter = new InfixToPostfix(infix);
		output = converter.generatePostfix(); // gera a string com o postfix

		//vai processar a string com o postfix
		//e tira espaços extra substituindo-os por apenas 1 espaço
		output = output.replaceAll("\\s+"," ");

		System.out.println("Expression in Postfix: "+ output);

		Scanner scanner = new Scanner(output);
		scanner.useDelimiter(" ");//faz o split por espaços

		StackArray<ExpNode> nodes = new StackArray<ExpNode>(output.length()); // 
		String s = "";

		/* FAZ scan da expressao em postfix e cria nodes até a stack ficar cheia apenas com um Node
    que contém a tree */

		while(scanner.hasNext()){
			s = scanner.next();

			/* avalia postfix guarda os nos da arvore na stack e examina cada parte da
        expressão da esquerda para a direita. Se ler um número cria um novo
        nó que é uma folha e faz push para a stack. Se ler um operador faz
        pop de 2 nós da stack, cria um nó de operador com esses 2 filhos e
        faz push desse nó de operador para a stack. No fim de processar a
        expressão em postfix a stack vai ter um nó apenas que é já a árvore
        construida em postfix.*/
			if(s.matches("[00-99]+")){
				ExpNode operand = new ExpNode(Integer.parseInt(s));
				nodes.push(operand);
			}
			else{
				ExpNode operand1 = nodes.pop();//direito
				ExpNode operand2 = nodes.pop();//esquerdo
				ExpNode operator = new ExpNode(s.charAt(0), operand2, operand1);
				//operator.setSize(2);
				nodes.push(operator);
			}
		}

		// depois da Arvore criada na stack
		ExpBinTree t = new ExpBinTree();
		t.setRoot(nodes.pop());

		t.draw("BinTree");

		return t;
	}


	//alinea 2.
	//avalia toda a expressão
	public static int evaluate(ExpBinTree t){
		return t.getRoot().eval();
	}

	//alinea 3
	public static DoublyLinkedList<Integer> operands(ExpBinTree t){

		ExpNode n = t.getRoot();

		operands_auxiliar(n);

		return ops;

	}

	//adiciona os operandos e desce pelo nó
	public static void operands_auxiliar(ExpNode n){
		if(n!= null){
			operands_auxiliar(n.getLeft());
			operands_auxiliar(n.getRight());
			if(n.isLeaf())
				ops.add(n.getValue());
		}
	}

	//alinea 4
	public static DoublyLinkedList<String> expression(ExpBinTree t){

		ExpNode n = t.getRoot();

		expression_auxiliar(n);

		return exp;

	}

	//adiciona os operadores e operandos e desce pelo nó
	public static void expression_auxiliar(ExpNode n){
		if (n != null){
			expression_auxiliar(n.getLeft());
			expression_auxiliar(n.getRight());
			exp.add(n.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Insira uma Expressão Infix : \n\n");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		//1.
		ExpBinTree t = new ExpBinTree();
		t = postfix(input);


		//2.
		System.out.println("Expression evaluation: "+evaluate(t));

		//3
		System.out.println("Operands list: " + operands(t).toString());

		//4
		System.out.println("Expression list: " + expression(t).toString());

	}

}
