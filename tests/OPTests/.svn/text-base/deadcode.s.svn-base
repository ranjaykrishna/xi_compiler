.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $104,%rsp
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
call _If_i
cmp $1,%rbx
jne extraPop0
popq %r13
extraPop0: 
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
popq %r13
movq %r13,-88(%rbp)
movq -88(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op1
movq $0,%r12
jmp _end1
_op1: 
movq $1,%r12
_end1: 
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
jnc rspJmp2
movq $1,%r12
jmp rspend2
rspJmp2: 
movq $0,%r12
rspend2: 
bt $3,%rbp
jnc rbpJmp2
movq $1,%r13
jmp rbpend2
rbpJmp2: 
movq $0,%r13
rbpend2: 
test %r12,%r13
jnp extraPush2
pushq $0
movq $1,%rbx
extraPush2: 
call _Iassert_pb
cmp $1,%rbx
jne extraPop2
popq %r13
extraPop2: 
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
ret3: 
cmp %rbp,%rsp
je retend3
popq %r12
jmp ret3
retend3: 
ret
.globl _If_i
_If_i: 
movq %rsp,%rbp
sub $120,%rsp
movq $5,%r12
pushq %r12
popq %r13
movq %r13,-88(%rbp)
movq $7,%r12
pushq %r12
popq %r13
movq %r13,-96(%rbp)
movq -88(%rbp),%r12
pushq %r12
movq -96(%rbp),%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
movq %r13,-104(%rbp)
movq $1,%r12
pushq %r12
popq %rax
ret4: 
cmp %rbp,%rsp
je retend4
popq %r12
jmp ret4
retend4: 
ret
.text
