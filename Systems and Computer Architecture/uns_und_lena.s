.data
	gray_file_name: .asciiz "imagem.gray"
	
	presentation: .asciiz "Trabalho prático ASCI by José Serra & Miguel Jesus \n\n "
	request_file: .asciiz "Por favor insira o nome de um ficheiro RGB: \n\n"
	loading: .asciiz "Aguarde enquanto as operações são efetuadas...\n\n"
	invalid_input: .asciiz "Erro ao abrir ficheiro, verifique se o nome que introduziu e valido! \n\n"
	error_writing_to_file: .asciiz "Erro de escrita no ficheiro, o programa vai terminar... \n\n"
	rgb_file_name: .space 100          #espaço para o user_input
	
	rgb_file_content: .space 786432    #espaço para o conteudo do ficheiro inserido
	gray_file_content: .space 262144   #espaço para o conteudo do buffer resultante das operações RGB-GRAY
	
	buffer_horizontal: .space 262144   #espaço para o conteudo das operações resultantes da matriz sobel horizontal sobre gray_file_content
	buffer_vertical: .space 262144	   #espaço para o conteudo das operações resultantes da matriz sobel vertical sobre gray_file_content
	buffer_contour: .space 262144	   #Espaço para o conteudo do buffer final SBh+SBv
	
	sobel_horizontal: .byte 1,0,-1,2,0,-2,1,0,-1	#sobel horizontal
	sobel_vertical: .byte 1,2,1,0,0,0,-1,-2,-1	#sobel vertical
.text

######################################################
# main
######################################################

main:
	la $a0, presentation		#Passa o endereço da string presentation como argumento em $a0
	li $v0, 4			#Imprime a String
	syscall
request:	
	#request file name
	la $a0,request_file		#Passa o endereço da string request_file como argumento em $a0
	li $v0,4			#Imprime a String
	syscall
	
	#insert file name
	la $a0,rgb_file_name		#Passa rgb_file_name como argumento em $a0
	li $a1,100			#Passa o tamanho do buffer como argumento em $a1
	li $v0,8			#user input
	syscall
	
	#corrige terminador da string rgb_file_name (passa de \n para 0)
	la $a0,rgb_file_name
	jal correct_string_terminator
	nop
	
	
	la $a0, loading			#Passa o endereço da string loading como argumento em $a0
	li $v0, 4			#Imprime a string
	syscall
	
	la $a0,rgb_file_name		#Passa o endereço do buffer que contém a string inserida pelo utilizador como argumento em $a0
	jal read_rgb_image		#Chama a função read_rgb_image
	nop
	
	bltz $v0,error_read_from_input	
	nop	
		
	move $a0, $v0	#Passa o conteudo do ficheiro RGB como argumento em $a0
	la $a1, gray_file_content	#Passa o endereço do buffer gray ainda vazio como argumento em $a1
	jal rgb_to_gray 		#chama a função rgb_to_gray
	nop
	
	la $a0, gray_file_content	#Passa o endereço do buffer resultante de rgb_to_gray como argumento em $a0
	la $a1, sobel_horizontal	#Passa o endereço da matriz sobel horizontal como argumento em $a1
	la $a2, buffer_horizontal	#Passa o endereço do buffer horizontal ainda vazio como argumento em $a2
	jal convolution			#chama a função convolution
	nop
	
	la $a0, gray_file_content 	#Igual à função anterior mas utiliza a matriz sobel e o buffer vertical
	la $a1, sobel_vertical		    
	la $a2, buffer_vertical		    
	jal convolution			    
	nop
	
	la $a0, buffer_horizontal	#Passa o endereço do buffer horizontal resultante da convolution como argumento em $a0
	la $a1, buffer_vertical		#Passa o endereço do buffer vertical resultante da convolution como argumento em $a1
	la $a2, buffer_contour		#Passa o endereço do buffer contour ainda vazio como argumento em $a2
	jal contour			#chama a função contour
	nop
	
	la $a0, gray_file_name		##Passa o nome do ficheiro gray como argumento em $a0
	la $a1, buffer_contour		#Passa o buffer resultante da função contour como argumento em $a1
	li $a2, 262144			#Passa o tamanho do ficheiro como argumento em $a2  (512x512)
	jal write_gray_image		#chama a função write_gray_image
	nop
		
	bltz $v0,error_write		
	nop	

end_program:	
	li $v0,10		#Fim do programa
	syscall
	
error_read_from_input:
	#erro no input
	la $a0,invalid_input
	li $v0,4
	syscall
	j request
	nop
	
error_write:
	#erro de escrita no ficheiro
	la $a0,error_writing_to_file
	li $v0,4
	syscall
	j end_program
	nop
	
######################################################
# read_rgb_image - esta função lê uma imagem no formato RGB para um array em memória.
#
# Argumentos:
# a0 - contém o endereço do buffer onde se encontra o conteúdo do user input
#
#retorna:
# v0 - o endereço do buffer que contém a imagem RGB
######################################################
	
read_rgb_image:	
	addi $sp,$sp,-8 	#espaço reservado na pilha
	sw $ra,0($sp) 
	sw $s0,4($sp)

	move $s0,$a0
	
	#open file
	la $a0,rgb_file_name
	li $a1,0
	li $a2,0
	li $v0,13
	syscall
	move $t0,$v0		#guarda o file descriptor

	#read file
	move $a0,$t0
        la $a1,rgb_file_content
        li $a2,786432
        li $v0, 14          
        syscall
        
        bltz $v0,end_read 	#em caso de erro a abrir ficheiro ou ler retorna valor negativo
        nop
        
        li $v0, 16		#close rgb file
        syscall
  
	la $v0,rgb_file_content #retorna endereço do buffer com conteudo do ficheiro rgb
   end_read:     
	lw $ra,0($sp)
	lw $s0,4($sp)
	addi $sp,$sp,8		#repõe o espaço na pilha
	
	jr $ra
	nop

######################################################
# write_gray_image - esta função escreve uma imagem em formato GRAY num ficheiro
#
# Argumentos:
# a0 - nome do ficheiro GRAY
# a1 - recebe o endereço de um buffer que contém dados
#a2 -  tamanho do ficheiro
######################################################

write_gray_image:
	move $t0, $a0                       
	move $t1, $a1                        
	move $t2, $a2                        
	
	la $a0, ($t0)
	li $a1, 1
	li $a2, 0
	li $v0, 13
	syscall
	
	move $a0,$v0
	la $a1,($t1) 		#grey_file_content
	la $a2,($t2) 		#48
	li $v0,15
	syscall
	
	li $v0, 16
	syscall	
	
	jr $ra
	nop

######################################################
# rgb_to_gray - esta função converte uma imagem a cores RGB para uma imagem em tons de cinzento GRAY.
#
# Argumentos:
# a0 - recebe o endereço do buffer com a imagem RGB
# a1 - recebe o endereço do buffer onde será colocada a imagem GRAY
######################################################

rgb_to_gray:		#I = 0.30R + 0.59G + 0.11B
	
	li $t5, 262144  		# i = 262144: número de vezes que o ciclo deve correr (512x512)
	
loop:
	beq $t5, $zero, loop_end 	#condição de saída do loop
	
	lbu $t0,0($a0)  		#load do byte 0
	lbu $t1,1($a0)			#load do byte 1
	lbu $t2,2($a0)			#load do byte 2
	
	mulu $t0, $t0, 3	        #operação sobre t0 de forma a aplicar a fórmula sobre a cor RED		
	divu $t0, $t0, 10		#  I = 0.30R + ...
	
	mulu $t1, $t1, 59		#operação sobre t0 de forma a aplicar a fórmula sobre a cor GREEN
	divu $t1, $t1, 100		#  I = ...+ 0.59G + ...
	
	mulu $t2, $t2, 11 		#operação sobre t0 de forma a aplicar a fórmula sobre a cor BLUE
	divu $t2, $t2, 100		#  I = ... + 0.11B 
	
	
	addu $t3, $t0, $t1		#Guarda no t3 a soma dos valores das conversoes RED e GREEN
	addu  $t3, $t3, $t2		#Adiciona em t3, o valor da soma obtida anteriormente juntamente com a conversão de BLUE
	
	sb $t3, 0($a1)			#Store do pixel resultante das conversões RGB, em a1
	
	addi $a0, $a0, 3		#Adiciona 3 ao a0, para avançar para os tres bytes seguintes
	addi $a1, $a1, 1		#Adiciona 1 ao a1 para avançar no gray_file_content
	addi $t5, $t5, -1		# decrementa o i
	j loop				#volta a loop
	nop
	
	loop_end:
					
	jr $ra				#Volta para main após saída do loop
	nop

######################################################
# convolution - esta função calcula a convolução de uma imagem com o operador Sobel
#
# Argumentos:
# a0 - recebe o endereço do buffer com a imagem GRAY
# a1 - recebe o endereço da matriz Sobel
# a2 - recebe o endereço do buffer onde será colocado o resultado das operações
######################################################

convolution:

	addi $sp, $sp, -32		#Aloca espaco na pilha para os 8 valores dos registos s
	
	sw $s0, 0($sp)			#Store do valor do registo s0 na pilha
	lb $s0, 0($a1)			#load sobel na posicao 0
	
	sw $s1, 4($sp)			#Store do valor do registo s1 na pilha
	lb $s1, 1($a1)			#load sobel na posicao 1
	
	sw $s2, 8($sp)			#Store do valor do registo s2 na pilha
	lb $s2, 2($a1)			#load sobel na posicao 2
	
	sw $s3, 12($sp)			#Store do valor do registo s3 na pilha
	lb $s3, 3($a1)			#load sobel na posicao 3
	
	sw $s4, 16($sp)			#Store do valor do registo s4 na pilha
	lb $s4, 5($a1)			#load sobel na posicao 5
	
	sw $s5, 20($sp)			#Store do valor do registo s5 na pilha
	lb $s5, 6($a1)			#load sobel na posicao 6
	
	sw $s6, 24($sp)			#Store do valor do registo s6 na pilha
	lb $s6, 7($a1)			#load sobel na posicaoo 7
	
	sw $s7, 28($sp)			#Store do valor do registo s7 na pilha
	lb $s7, 8($a1)			#load sobel na posicao 8
	
	li $t0, 0 	#i=0
	li $t2, 513 	#posi
	li $t3, 0 	#count

for:

	bgt $t0,260099,end_for 
	addi $t0, $t0, 1 	# i++
	nop
	add $t4,$t2,$a0 	#Endereço onde se encontra o buffer + posi  #gray file content
	add $t4, $t4, $t3	#adiciona counter
	
	add $t8, $t2, $a2	#Buffer vazio, começa na Posi       #buffer_horizontal             
	add $t8, $t8, $t3	#adiciona counter
	
	#operações
	lbu $t6, -513($t4)	#guarda em $t6 o primeiro valor da matriz	
	mul $t6, $t6, $s0	#multiplica esse valor pelo valor correspondente da matriz sobel	
	add $t7, $t6, $zero	#guarda o valor final em $t7
	
		
	lbu $t6, -512($t4)		
	mul $t6, $t6, $s1		
	add $t7, $t6, $t7
	
	lbu $t6, -511($t4)		
	mul $t6, $t6, $s2		
	add $t7, $t6, $t7
		
	lbu $t6, -1($t4)		
	mul $t6, $t6, $s3		
	add $t7, $t6, $t7
		
	lbu $t6, 1($t4)		
	mul $t6, $t6, $s4		
	add $t7, $t6, $t7
		
	lbu $t6, 511($t4)		
	mul $t6, $t6, $s5		
	add $t7, $t6, $t7
		
	lbu $t6, 512($t4)		
	mul $t6, $t6, $s6		
	add $t7, $t6, $t7
		
	lbu $t6, 513($t4)		
	mul $t6, $t6, $s7		
	add $t7, $t6, $t7
	
	abs $t7, $t7		#Valor absoluto de $t7
		
	sb $t7,0($t8)		#guarda o valor resultante de todas as somas da operações de $t7 em $t8 (buffer vazio) (horizontal||vertical)

	beq $t3,509,jump_line 	#if count == 509 (Se chegou à penultima posição da linha)
	nop
	j inc_count		#Caso ainda não tenha chegado à penultima posição da linha, incrementa o contador
	nop
		 	 	 	 	 	 	 	 	 	 	 
jump_line:

	addi $t2,$t2,512	#posi+=512 (Passa para a Segunda posição da linha de baixo (PosInicial + 512) )
	li $t3,0		#count=0   (reinicia o contador da linha)
	j for			#Volta a for
	nop
	
inc_count:

	addi $t3,$t3,1		# count = count + 1
	j for 
	nop
	
end_for:

	lw $s0, 0($sp)		#reposição da pilha
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	lw $s5, 20($sp)
	lw $s6, 24($sp)
	lw $s7, 28($sp)
		
	addi $sp, $sp, 32
	jr $ra
	nop
	
######################################################
# contour - esta função calcula a imagem final combinando duas imagens filtradas pelos operadores Sobel
#
# Argumentos:
# a0 - recebe o endereço do buffer que contém a convolução horizontal
# a1 - recebe o endereço do buffer que contém a convolução vertical
# a2 - recebe o endereço do buffer onde será colocado o resultado das operações, ou seja, a imagem final
######################################################
	
contour:
	
	li $t0, 0		# i = 0
	
        cycle:
        
	bge $t0, 262144, end_cycle	# if i = 262144
	nop
	
	lbu $t4,($a0)		#load byte de buffer_horizontal
	lbu $t5,($a1)		#load byte de buffer_vertical
	
	add $t6, $t5, $t4	#soma dos dois valores  (Bh + Bv)
	div $t6, $t6, 8		# 1/2 ( 1/4*Bh + 1/4*Bv)  = 1/8 / (Bh+Bv)
	sb $t6, 0($a2)		#store do resultado em buffer_contour
	
	addi $a0, $a0, 1	#incrementa posição em buffer_horizontal
	addi $a1, $a1, 1	#incrementa posição em buffer_vertical
	addi $a2, $a2, 1	#incrementa posição em buffer_contour
	
	addi $t0, $t0, 1 	#incrementa i
	
	j cycle			#volta a cycle
	nop
	
end_cycle:

	jr $ra					
	nop	

######################################################
# correct_string_terminator - troca o caracter terminador de uma string ("\n") por "0"
#
# Argumentos:
# a0 - contém o endereço do buffer onde se encontra uma string com  caracter terminador "\n"
#
#
######################################################

correct_string_terminator:
	
while:

	lb $t0,0($a0)
	beq $t0,0xa,end_while
	nop
	addi $a0,$a0,1
	j while
	
end_while:

	li $t0,0
	sb $t0,($a0)
	jr $ra
	nop