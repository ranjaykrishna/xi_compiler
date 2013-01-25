.text
.globl _Imain_pai
_Imain_pai: 
movq %rsp,%rbp
sub $96,%rsp
movq $5,%r10
movq $7,%r11
pushq %r11
movq %r10,%r12
popq %r13
add %r12,%r13
movq %r13,%r10
movq %r10,%r10
pushq %r10
movq $15,%r12
popq %r13
add %r12,%r13
movq %r13,%r10
movq %r10,%r10
pushq %r10
movq $99,%r12
popq %r13
cmp %r12,%r13
je _true0
movq $0,%r12
jmp _end0
_true0: 
movq $1,%r12
_end0: 
movq %r12,%r10
movq %r10,%r10
cmp $1,%r10
je if_true0
if_false1: 
jmp if_end2
if_end2: 
ret1: 
cmp %rbp,%rsp
je retend1
popq %r12
jmp ret1
retend1: 
ret
if_true0: 
jmp if_end2
