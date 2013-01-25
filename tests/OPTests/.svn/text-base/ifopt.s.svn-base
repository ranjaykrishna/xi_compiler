.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $104,%rsp
movq $3,%r12
pushq %r12
popq %r13
movq %r13,-88(%rbp)
movq $1,%r12
pushq %r12
popq %r13
movq %r13,-88(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-96(%rbp)
movq -88(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op0
movq $0,%r12
jmp _end0
_op0: 
movq $1,%r12
_end0: 
pushq %r12
popq %r13
movq %r13,-96(%rbp)
movq %rcx,-16(%rbp)
movq %rdx,-24(%rbp)
movq %rsp,-32(%rbp)
movq %rsi,-48(%rbp)
movq %rdi,-40(%rbp)
movq %r8,-56(%rbp)
movq %r9,-64(%rbp)
movq %r10,-72(%rbp)
movq %r11,-80(%rbp)
pushq %rbx
pushq %rbp
movq -96(%rbp),%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp1
movq $1,%r12
jmp rspend1
rspJmp1: 
movq $0,%r12
rspend1: 
bt $3,%rbp
jnc rbpJmp1
movq $1,%r13
jmp rbpend1
rbpJmp1: 
movq $0,%r13
rbpend1: 
test %r12,%r13
jnp extraPush1
pushq $0
movq $1,%rbx
extraPush1: 
call _Iassert_pb
cmp $1,%rbx
jne extraPop1
popq %r13
extraPop1: 
movq $0,%rbx
popq %rbp
popq %rbx
movq -80(%rbp),%r11
movq -72(%rbp),%r10
movq -64(%rbp),%r9
movq -56(%rbp),%r8
movq -40(%rbp),%rdi
movq -48(%rbp),%rsi
movq -32(%rbp),%rsp
movq -24(%rbp),%rdx
movq -16(%rbp),%rcx
pushq %rax
popq %r12
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
.text
