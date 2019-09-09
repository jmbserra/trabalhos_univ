package expressoes;

public class InfixToPostfix {

	private StackArray<String> expStack;
	private String input;
	private String output;
	private String value = "";

	public InfixToPostfix(String in) {
		input = in;
		int stackSize = input.length();
		output = "";
		expStack = new StackArray<String>(stackSize);
	}

	public String generatePostfix() {
		for (int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			switch (ch) {
			case '+':
			case '-':
				output = output + " "+ value;
				value = "";
				//reads +, - prioridade 1
				gotOp(""+ch, 1);
				output+=" ";
				break;
			case '*':
			case '/':
				output = output + " "+ value;
				value = "";
				//reads *, / prioridade 2
				gotOp(""+ch, 2);

				output+=" ";
				break;
			case '(':
				output = output + " "+ value;
				value = "";
				expStack.push(""+ch);
				break;
			case ')':
				output = output + " "+ value;
				value = "";
				gotParen(""+ch);
				output += " ";
				break;
			default:
				value = value + ch;
				break;
			}
		}

		//junta ao output o último número que foi processado no switch case
		output = output + " "+ value;

		while (!expStack.isEmpty()) {
			output = output + " "+ expStack.pop();
		}

		return output;
	}


	public void gotOp(String op, int precedence) {
		while (!expStack.isEmpty()) {
			String opTop = expStack.pop();
			if (opTop == "(") {
				expStack.push(opTop);
				break;
			}
			else {
				int prec;
				if (opTop == "+" || opTop == "-")
					prec = 1;
				else
					prec = 2;
				if (prec < precedence) {
					expStack.push(opTop);
					break;
				}
				else
					output = output + " " + opTop;
			}
		}

		expStack.push(op);
	}


	public void gotParen(String ch){
		while (!expStack.isEmpty()) {
			String chx = expStack.pop();
			if (chx == "(")
				break;
			else
				output = output +" "+ chx;
		}
	}
}
