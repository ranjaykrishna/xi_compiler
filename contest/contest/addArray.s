.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-8(%rbp)
pushq $0
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
movq $32,%r12
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
popq %r13
movq %r13,-16(%rbp)
movq $3,%r12
pushq %r12
movq -16(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $65,%r12
pushq %r12
movq -16(%rbp),%r12
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
movq $66,%r12
pushq %r12
movq -16(%rbp),%r12
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
movq $67,%r12
pushq %r12
movq -16(%rbp),%r12
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
movq -16(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-8(%rbp)
_block0End_1:
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
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
movq $48,%r12
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
call _I_alloc_i
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
popq %r13
movq %r13,-24(%rbp)
movq $5,%r12
pushq %r12
movq -24(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $104,%r12
pushq %r12
movq -24(%rbp),%r12
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
movq -24(%rbp),%r12
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
movq $108,%r12
pushq %r12
movq -24(%rbp),%r12
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
movq $108,%r12
pushq %r12
movq -24(%rbp),%r12
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
movq $111,%r12
pushq %r12
movq -24(%rbp),%r12
pushq %r12
movq $40,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
pushq $0
movq -24(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
_block1End_1:
popq %r13
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
pushq $0
movq -32(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
popq %r13
movq %r13,-40(%rbp)
pushq $0
movq -40(%rbp),%r12
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
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
movq $8,%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
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
call _I_alloc_i
cmp $1,%rbx
jne extraPop2
popq %r13
extraPop2: 
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
popq %r13
movq %r13,-56(%rbp)
movq -48(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -56(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
w_head2: 
movq -64(%rbp),%r12
pushq %r12
movq -40(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op3
movq $0,%r12
jmp _end3
_op3: 
movq $1,%r12
_end3: 
pushq %r12
popq %r12
btc $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true3
w_false4: 
movq -32(%rbp),%r12
pushq %r12
movq -64(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq -56(%rbp),%r12
pushq %r12
movq -64(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -64(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
jmp w_head2
w_true3: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-72(%rbp)
w_head6: 
movq -64(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op4
movq $0,%r12
jmp _end4
_op4: 
movq $1,%r12
_end4: 
pushq %r12
popq %r12
btc $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true7
w_false8: 
movq -32(%rbp),%r12
pushq %r12
movq -72(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq -56(%rbp),%r12
pushq %r12
movq -64(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -64(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
movq -72(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-72(%rbp)
jmp w_head6
w_true7: 
movq -56(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
_block10End_7:
popq %r13
popq %r13
popq %r13
popq %r13
popq %r13
popq %r13
popq %r13
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
movq -16(%rbp),%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp5
movq $1,%r12
jmp rspend5
rspJmp5: 
movq $0,%r12
rspend5: 
bt $3,%rbp
jnc rbpJmp5
movq $1,%r13
jmp rbpend5
rbpJmp5: 
movq $0,%r13
rbpend5: 
test %r12,%r13
jnp extraPush5
pushq $0
movq $1,%rbx
extraPush5: 
call _Iprintln_pai
cmp $1,%rbx
jne extraPop5
popq %r13
extraPop5: 
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
popq %r12
_block11End_2:
popq %r13
popq %r13
ret6: 
cmp %rbp,%rsp
je retend6
popq %r12
jmp ret6
retend6: 
ret
.text
