.text
.globl _Ifoo_ii
_Ifoo_ii: 
movq %rsp,%rbp
sub $96,%rsp
movq $1,%rax
ret0: 
cmp %rbp,%rsp
je retend0
popq %r12
jmp ret0
retend0: 
ret
