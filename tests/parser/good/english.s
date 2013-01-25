.text
.globl _Imain_pai
_Imain_pai: 
movq %rsp,%rbp
sub $96,%rsp
w_head0: 
cmp $1,$0
je w_true1
w_false2: 
movq $0,%r10
movq %r10,%r10
cmp $1,%r10
je if_true3
if_false4: 
jmp if_end5
if_end5: 
jmp w_head0
if_true3: 
jmp w_head0
w_true1: 
ret0: 
cmp %rbp,%rsp
je retend0
popq %r12
jmp ret0
retend0: 
ret
