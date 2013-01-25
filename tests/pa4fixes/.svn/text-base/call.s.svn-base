.text
.globl _Imain_iaai
_Imain_iaai: 
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
movq $2,%r12
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
call _If_ii
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
movq -8(%rbp),%r12
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
call _If_ii
cmp $1,%rbx
jne extraPop1
popq %r13
extraPop1: 
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
movq -16(%rbp),%r12
pushq %r12
movq $2,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %rax
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
_block0End_0:
.globl _If_ii
_If_ii: 
movq %rsp,%rbp
movq %rdi,%r12
pushq %r12
movq $2,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %rax
ret3: 
cmp %rbp,%rsp
je retend3
popq %r12
jmp ret3
retend3: 
ret
_block1End_0:
.text
