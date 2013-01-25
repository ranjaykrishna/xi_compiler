.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
pushq %rcx
pushq %rdx
pushq %rsp
pushq %rsi
pushq %rdi
pushq %r8
pushq %r9
pushq %r10
pushq %r11
pushq %rbx
pushq %rbp
movq $5,%r12
pushq %r12
popq %r13
movq %r13,%rdi
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
popq %r11
popq %r10
popq %r9
popq %r8
popq %rdi
popq %rsi
popq %rsp
popq %rdx
popq %rcx
pushq %rax
movq $4,%r12
pushq %r12
movq -8(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $116,%r12
pushq %r12
movq -8(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $101,%r12
pushq %r12
movq -8(%rbp),%r12
pushq %r12
movq $16,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $120,%r12
pushq %r12
movq -8(%rbp),%r12
pushq %r12
movq $24,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $116,%r12
pushq %r12
movq -8(%rbp),%r12
pushq %r12
movq $32,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
_block0End_1:
popq %r13
movq -8(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
_block1End_1:
popq %r13
ret1: 
cmp %rbp,%rsp
je retend1
popq %r12
jmp ret1
retend1: 
ret
.text
