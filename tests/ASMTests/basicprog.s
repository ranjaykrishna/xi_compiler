.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $96,%rsp
movq $0,%r10
movq %r10,%r10
cmp $1,%r10
je if_true0
if_false1: 
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
pushq $32
popq %rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp0
movq $1,%r12
jmp rspend0
rspJmp0: 
movq $0,%r12
rspend0: 
bt $3,%rbp
jnc rbpJmp0
movq $1,%r13
jmp rbpend0
rbpJmp0: 
movq $0,%r13
rbpend0: 
test %r12,%r13
jnp extraPush0
pushq $0
movq $1,%rbx
extraPush0: 
call _I_alloc_i
cmp $1,%rbx
jne extraPop0
popq %r13
extraPop0: 
movq $0,%rbx
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
movq %rax,%r15
pushq $3
popq %r12
movq %r12,(%r15)
pushq %r15
movq $8,%r12
popq %r13
add %r12,%r13
movq %r13,%r10
pushq $89
popq %r12
movq %r12,(%r10)
pushq %r15
movq $16,%r12
popq %r13
add %r12,%r13
movq %r13,%r11
pushq $69
popq %r12
movq %r12,(%r11)
pushq %r15
movq $24,%r12
popq %r13
add %r12,%r13
movq %r13,%r15
pushq $65
popq %r12
movq %r12,(%r15)
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
call _Iprintln_pai
cmp $1,%rbx
jne extraPop1
popq %r13
extraPop1: 
movq $0,%rbx
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
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
if_true0: 
ret3: 
cmp %rbp,%rsp
je retend3
popq %r12
jmp ret3
retend3: 
ret
.text
