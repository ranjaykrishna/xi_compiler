.text
.globl _Imain_pii
_Imain_pii: 
movq %rsp,%rbp
sub $96,%rsp
movq $100,%r15
movq $100,%r11
w_head0: 
pushq %r15
movq $0,%r12
popq %r13
cmp %r12,%r13
jg _true0
movq $0,%r12
jmp _end0
_true0: 
movq $1,%r12
_end0: 
btc $0,%r12
cmp $1,%r12
je w_true1
w_false2: 
pushq %r15
movq $1,%r12
popq %r13
subq %r12,%r13
movq %r13,%r15
w_head3: 
pushq %r11
movq $0,%r12
popq %r13
cmp %r12,%r13
jg _true1
movq $0,%r12
jmp _end1
_true1: 
movq $1,%r12
_end1: 
btc $0,%r12
movq %r12,%r10
cmp $1,%r10
je w_true4
w_false5: 
pushq %r11
movq $1,%r12
popq %r13
subq %r12,%r13
movq %r13,%r11
w_head6: 
cmp $1,$0
je w_true7
w_false8: 
jmp w_true7
w_true7: 
jmp w_head3
w_true4: 
jmp w_head0
w_true1: 
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
