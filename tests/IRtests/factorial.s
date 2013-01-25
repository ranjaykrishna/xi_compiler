.text
.globl _Imain_i
_Imain_i: 
movq %rsp,%rbp
sub $96,%rsp
movq %rcx,-16(%rbp)
movq %rdx,-24(%rbp)
movq %rsp,-32(%rbp)
movq %rsi,-48(%rbp)
movq %rdi,-40(%rbp)
movq %r8,-56(%rbp)
movq %r9,-64(%rbp)
movq %r10,-72(%rbp)
movq %r11,-80(%rbp)
movq %r15,-88(%rbp)
pushq %rbx
pushq %rbp
pushq $10
popq %rdi
call _Ifactorial_ii
popq %rbp
popq %rbx
movq -88(%rbp),%r15
movq -80(%rbp),%r11
movq -72(%rbp),%r10
movq -64(%rbp),%r9
movq -56(%rbp),%r8
movq -40(%rbp),%rdi
movq -48(%rbp),%rsi
movq -32(%rbp),%rsp
movq -24(%rbp),%rdx
movq -16(%rbp),%rcx
movq %rax,%rax
ret0: 
cmp %rbp,%rsp
je retend0
popq %r12
jmp ret0
retend0: 
ret
.globl _Ifactorial_ii
_Ifactorial_ii: 
movq %rsp,%rbp
sub $96,%rsp
movq %rdi,%rdi
pushq %rdi
movq $1,%r12
popq %r13
cmp %r12,%r13
js _true1
movq $0,%r12
jmp _end1
_true1: 
movq $1,%r12
_end1: 
movq %r12,%r10
movq %r10,%r10
cmp $1,%r10
je if_true0
if_false1: 
pushq %rdi
movq $1,%r12
popq %r13
subq %r12,%r13
movq %r13,%r10
movq %r10,%r10
movq %rcx,-16(%rbp)
movq %rdx,-24(%rbp)
movq %rsp,-32(%rbp)
movq %rsi,-48(%rbp)
movq %rdi,-40(%rbp)
movq %r8,-56(%rbp)
movq %r9,-64(%rbp)
movq %r10,-72(%rbp)
movq %r11,-80(%rbp)
movq %r15,-88(%rbp)
pushq %rbx
pushq %rbp
pushq %r10
popq %rdi
call _Ifactorial_ii
popq %rbp
popq %rbx
movq -88(%rbp),%r15
movq -80(%rbp),%r11
movq -72(%rbp),%r10
movq -64(%rbp),%r9
movq -56(%rbp),%r8
movq -40(%rbp),%rdi
movq -48(%rbp),%rsi
movq -32(%rbp),%rsp
movq -24(%rbp),%rdx
movq -16(%rbp),%rcx
movq %rax,%r10
pushq %rdi
movq %r10,%r12
popq %r13
imul %r12,%r13
movq %r13,%rdi
movq %rdi,%rax
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
if_true0: 
movq $1,%rax
ret3: 
cmp %rbp,%rsp
je retend3
popq %r12
jmp ret3
retend3: 
ret
