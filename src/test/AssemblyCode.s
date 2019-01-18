.data
testVar: .word 3
.text
main:
li $a0 22
sw $a0 ($sp)
addiu $sp $sp -4
li $a0 28
lw $t0 4($sp)
addiu $sp $sp 4
add $a0 $a0 $t0
li $a0 22
sw $a0 ($sp)
addiu $sp $sp -4
li $a0 32
lw $t0 4($sp)
addiu $sp $sp 4
blt $t0 $a0 branch0
li $a0 0
b branch_over0
branch0:
li $a0 1
branch_over0:
li $a0 22
sw $a0 testVar
li $v0 1
syscall
li $v0 10
syscall