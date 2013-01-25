.text
.globl _Iitoa_aiii
_Iitoa_aiii: 
movq %rsp,%rbp
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-8(%rbp)
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
movq -16(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
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
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
movq -16(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-8(%rbp)
_block2End_2:
popq %r13
popq %r13
w_head3: 
movq $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true4
w_false5: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
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
movq -24(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
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
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
movq -24(%rbp),%r12
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -32(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
_block8End_2:
popq %r13
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
w_head9: 
movq -24(%rbp),%r12
pushq %r12
movq -8(%rbp),%r12
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
cmp %r13,%r12
js _op2
movq $0,%r12
jmp _end2
_op2: 
movq $1,%r12
_end2: 
pushq %r12
popq %r12
btc $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true10
w_false11: 
pushq $0
movq -24(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
pushq $0
movq -16(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
movq -32(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op3
movq $0,%r12
jmp _end3
_op3: 
movq $1,%r12
_end3: 
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq -40(%rbp),%r12
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
cmp %r13,%r12
js _op4
movq $0,%r12
jmp _end4
_op4: 
movq $1,%r12
_end4: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds21
f_outOfBounds20: 
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
call _I_outOfBounds_p
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
t_inBounds21: 
jmp b_lookup22
b_lookup22: 
pushq $0
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
movq -48(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op6
movq $0,%r12
jmp _end6
_op6: 
movq $1,%r12
_end6: 
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
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
cmp %r13,%r12
js _op7
movq $0,%r12
jmp _end7
_op7: 
movq $1,%r12
_end7: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds14
f_outOfBounds13: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp8
movq $1,%r12
jmp rspend8
rspJmp8: 
movq $0,%r12
rspend8: 
bt $3,%rbp
jnc rbpJmp8
movq $1,%r13
jmp rbpend8
rbpJmp8: 
movq $0,%r13
rbpend8: 
test %r12,%r13
jnp extraPush8
pushq $0
movq $1,%rbx
extraPush8: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop8
popq %r13
extraPop8: 
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
t_inBounds14: 
jmp b_lookup15
b_lookup15: 
movq -56(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq -40(%rbp),%r12
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
_block25End_2:
popq %r13
popq %r13
_block18End_2:
popq %r13
popq %r13
movq -24(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
_block26End_0:
jmp w_head9
w_true10: 
movq -16(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-8(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
movq -32(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op9
movq $0,%r12
jmp _end9
_op9: 
movq $1,%r12
_end9: 
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq -40(%rbp),%r12
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
cmp %r13,%r12
js _op10
movq $0,%r12
jmp _end10
_op10: 
movq $1,%r12
_end10: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds29
f_outOfBounds28: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp11
movq $1,%r12
jmp rspend11
rspJmp11: 
movq $0,%r12
rspend11: 
bt $3,%rbp
jnc rbpJmp11
movq $1,%r13
jmp rbpend11
rbpJmp11: 
movq $0,%r13
rbpend11: 
test %r12,%r13
jnp extraPush11
pushq $0
movq $1,%rbx
extraPush11: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop11
popq %r13
extraPop11: 
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
t_inBounds29: 
jmp b_lookup30
b_lookup30: 
movq $48,%r12
pushq %r12
movq %rdi,%r12
pushq %r12
movq %rsi,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,%rax
cmp $0,%r12
jl div12
movq $0,%rdx
jmp divEnd12
div12:
movq $-1,%rdx
divEnd12:
idiv %r13
pushq %rdx
popq %r13
popq %r12
add %r13,%r12
pushq %r12
movq -40(%rbp),%r12
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
_block33End_2:
popq %r13
popq %r13
pushq $0
movq %rdi,%r12
pushq %r12
movq %rsi,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,%rax
cmp $0,%r12
jl div13
movq $0,%rdx
jmp divEnd13
div13:
movq $-1,%rdx
divEnd13:
idiv %r13
pushq %rax
popq %r13
movq %r13,%rdi
pushq $0
movq %rdi,%r12
pushq %r12
movq $0,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op14
movq $0,%r12
jmp _end14
_op14: 
movq $1,%r12
_end14: 
pushq %r12
popq %r13
movq %r13,-40(%rbp)
movq -40(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je if_true34
if_false35: 
jmp if_end36
if_true34: 
jmp w_true4
jmp if_end36
if_end36: 
_block38End_1:
popq %r13
_block39End_2:
popq %r13
popq %r13
jmp w_head3
w_true4: 
movq -8(%rbp),%r12
pushq %r12
popq %rax
ret15: 
cmp %rbp,%rsp
je retend15
popq %r12
jmp ret15
retend15: 
ret
_block40End_1:
popq %r13
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
movq -16(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op16
movq $0,%r12
jmp _end16
_op16: 
movq $1,%r12
_end16: 
pushq %r12
movq -16(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
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
cmp %r13,%r12
js _op17
movq $0,%r12
jmp _end17
_op17: 
movq $1,%r12
_end17: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds43
f_outOfBounds42: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp18
movq $1,%r12
jmp rspend18
rspJmp18: 
movq $0,%r12
rspend18: 
bt $3,%rbp
jnc rbpJmp18
movq $1,%r13
jmp rbpend18
rbpJmp18: 
movq $0,%r13
rbpend18: 
test %r12,%r13
jnp extraPush18
pushq $0
movq $1,%rbx
extraPush18: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop18
popq %r13
extraPop18: 
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
t_inBounds43: 
jmp b_lookup44
b_lookup44: 
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
movq $10,%r12
pushq %r12
popq %r13
movq %r13,%rsi
movq $153,%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp19
movq $1,%r12
jmp rspend19
rspJmp19: 
movq $0,%r12
rspend19: 
bt $3,%rbp
jnc rbpJmp19
movq $1,%r13
jmp rbpend19
rbpJmp19: 
movq $0,%r13
rbpend19: 
test %r12,%r13
jnp extraPush19
pushq $0
movq $1,%rbx
extraPush19: 
call _Iitoa_aiii
cmp $1,%rbx
jne extraPop19
popq %r13
extraPop19: 
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
movq -24(%rbp),%r12
pushq %r12
movq -16(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
_block47End_2:
popq %r13
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
movq -48(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op20
movq $0,%r12
jmp _end20
_op20: 
movq $1,%r12
_end20: 
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
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
cmp %r13,%r12
js _op21
movq $0,%r12
jmp _end21
_op21: 
movq $1,%r12
_end21: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds50
f_outOfBounds49: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp22
movq $1,%r12
jmp rspend22
rspJmp22: 
movq $0,%r12
rspend22: 
bt $3,%rbp
jnc rbpJmp22
movq $1,%r13
jmp rbpend22
rbpJmp22: 
movq $0,%r13
rbpend22: 
test %r12,%r13
jnp extraPush22
pushq $0
movq $1,%rbx
extraPush22: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop22
popq %r13
extraPop22: 
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
t_inBounds50: 
jmp b_lookup51
b_lookup51: 
movq -56(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $8,%r12
pushq %r12
popq %r13
popq %r12
subq %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $3,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op23
movq $0,%r12
jmp _end23
_op23: 
movq $1,%r12
_end23: 
pushq %r12
popq %r12
cmp $1,%r12
je shortANDTrue68
_block54End_2:
popq %r13
popq %r13
shortANDFalse69: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
jmp shortANDBottom70
shortANDTrue68: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
movq -48(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op24
movq $0,%r12
jmp _end24
_op24: 
movq $1,%r12
_end24: 
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
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
cmp %r13,%r12
js _op25
movq $0,%r12
jmp _end25
_op25: 
movq $1,%r12
_end25: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds57
f_outOfBounds56: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp26
movq $1,%r12
jmp rspend26
rspJmp26: 
movq $0,%r12
rspend26: 
bt $3,%rbp
jnc rbpJmp26
movq $1,%r13
jmp rbpend26
rbpJmp26: 
movq $0,%r13
rbpend26: 
test %r12,%r13
jnp extraPush26
pushq $0
movq $1,%rbx
extraPush26: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop26
popq %r13
extraPop26: 
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
t_inBounds57: 
jmp b_lookup58
b_lookup58: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
pushq $0
movq -56(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
popq %r13
movq %r13,-72(%rbp)
movq -64(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op27
movq $0,%r12
jmp _end27
_op27: 
movq $1,%r12
_end27: 
pushq %r12
movq -64(%rbp),%r12
pushq %r12
movq -72(%rbp),%r12
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
cmp %r13,%r12
js _op28
movq $0,%r12
jmp _end28
_op28: 
movq $1,%r12
_end28: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds62
f_outOfBounds61: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp29
movq $1,%r12
jmp rspend29
rspJmp29: 
movq $0,%r12
rspend29: 
bt $3,%rbp
jnc rbpJmp29
movq $1,%r13
jmp rbpend29
rbpJmp29: 
movq $0,%r13
rbpend29: 
test %r12,%r13
jnp extraPush29
pushq $0
movq $1,%rbx
extraPush29: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop29
popq %r13
extraPop29: 
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
t_inBounds62: 
jmp b_lookup63
b_lookup63: 
movq -72(%rbp),%r12
pushq %r12
movq -64(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $49,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op30
movq $0,%r12
jmp _end30
_op30: 
movq $1,%r12
_end30: 
pushq %r12
popq %r13
movq %r13,-40(%rbp)
_block66End_2:
popq %r13
popq %r13
_block67End_2:
popq %r13
popq %r13
shortANDBottom70: 
movq -40(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je shortANDTrue86
_block72End_1:
popq %r13
shortANDFalse87: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
jmp shortANDBottom88
shortANDTrue86: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
movq -40(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op31
movq $0,%r12
jmp _end31
_op31: 
movq $1,%r12
_end31: 
pushq %r12
movq -40(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
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
cmp %r13,%r12
js _op32
movq $0,%r12
jmp _end32
_op32: 
movq $1,%r12
_end32: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds75
f_outOfBounds74: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp33
movq $1,%r12
jmp rspend33
rspJmp33: 
movq $0,%r12
rspend33: 
bt $3,%rbp
jnc rbpJmp33
movq $1,%r13
jmp rbpend33
rbpJmp33: 
movq $0,%r13
rbpend33: 
test %r12,%r13
jnp extraPush33
pushq $0
movq $1,%rbx
extraPush33: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop33
popq %r13
extraPop33: 
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
t_inBounds75: 
jmp b_lookup76
b_lookup76: 
pushq $0
movq $1,%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
pushq $0
movq -48(%rbp),%r12
pushq %r12
movq -40(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
popq %r13
movq %r13,-64(%rbp)
movq -56(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op34
movq $0,%r12
jmp _end34
_op34: 
movq $1,%r12
_end34: 
pushq %r12
movq -56(%rbp),%r12
pushq %r12
movq -64(%rbp),%r12
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
cmp %r13,%r12
js _op35
movq $0,%r12
jmp _end35
_op35: 
movq $1,%r12
_end35: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds80
f_outOfBounds79: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp36
movq $1,%r12
jmp rspend36
rspJmp36: 
movq $0,%r12
rspend36: 
bt $3,%rbp
jnc rbpJmp36
movq $1,%r13
jmp rbpend36
rbpJmp36: 
movq $0,%r13
rbpend36: 
test %r12,%r13
jnp extraPush36
pushq $0
movq $1,%rbx
extraPush36: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop36
popq %r13
extraPop36: 
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
t_inBounds80: 
jmp b_lookup81
b_lookup81: 
movq -64(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $53,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op37
movq $0,%r12
jmp _end37
_op37: 
movq $1,%r12
_end37: 
pushq %r12
popq %r13
movq %r13,-32(%rbp)
_block84End_2:
popq %r13
popq %r13
_block85End_2:
popq %r13
popq %r13
shortANDBottom88: 
movq -32(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je shortANDTrue104
_block90End_1:
popq %r13
shortANDFalse105: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
jmp shortANDBottom106
shortANDTrue104: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-40(%rbp)
movq -32(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op38
movq $0,%r12
jmp _end38
_op38: 
movq $1,%r12
_end38: 
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq -40(%rbp),%r12
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
cmp %r13,%r12
js _op39
movq $0,%r12
jmp _end39
_op39: 
movq $1,%r12
_end39: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds93
f_outOfBounds92: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp40
movq $1,%r12
jmp rspend40
rspJmp40: 
movq $0,%r12
rspend40: 
bt $3,%rbp
jnc rbpJmp40
movq $1,%r13
jmp rbpend40
rbpJmp40: 
movq $0,%r13
rbpend40: 
test %r12,%r13
jnp extraPush40
pushq $0
movq $1,%rbx
extraPush40: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop40
popq %r13
extraPop40: 
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
t_inBounds93: 
jmp b_lookup94
b_lookup94: 
pushq $0
movq $2,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
movq -40(%rbp),%r12
pushq %r12
movq -32(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
popq %r13
movq %r13,-56(%rbp)
movq -48(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op41
movq $0,%r12
jmp _end41
_op41: 
movq $1,%r12
_end41: 
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq -56(%rbp),%r12
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
cmp %r13,%r12
js _op42
movq $0,%r12
jmp _end42
_op42: 
movq $1,%r12
_end42: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds98
f_outOfBounds97: 
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
movq $0,%rbx
bt $3,%rsp
jnc rspJmp43
movq $1,%r12
jmp rspend43
rspJmp43: 
movq $0,%r12
rspend43: 
bt $3,%rbp
jnc rbpJmp43
movq $1,%r13
jmp rbpend43
rbpJmp43: 
movq $0,%r13
rbpend43: 
test %r12,%r13
jnp extraPush43
pushq $0
movq $1,%rbx
extraPush43: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop43
popq %r13
extraPop43: 
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
t_inBounds98: 
jmp b_lookup99
b_lookup99: 
movq -56(%rbp),%r12
pushq %r12
movq -48(%rbp),%r12
pushq %r12
movq $3,%r12
pushq %r12
popq %r13
popq %r12
shl $3,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r12
pushq (%r12)
movq $51,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
je _op44
movq $0,%r12
jmp _end44
_op44: 
movq $1,%r12
_end44: 
pushq %r12
popq %r13
movq %r13,-24(%rbp)
_block102End_2:
popq %r13
popq %r13
_block103End_2:
popq %r13
popq %r13
shortANDBottom106: 
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
_block108End_1:
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
jnc rspJmp45
movq $1,%r12
jmp rspend45
rspJmp45: 
movq $0,%r12
rspend45: 
bt $3,%rbp
jnc rbpJmp45
movq $1,%r13
jmp rbpend45
rbpJmp45: 
movq $0,%r13
rbpend45: 
test %r12,%r13
jnp extraPush45
pushq $0
movq $1,%rbx
extraPush45: 
call _Iassert_pb
cmp $1,%rbx
jne extraPop45
popq %r13
extraPop45: 
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
_block111_End_1:
popq %r13
_block109End_0:
ret46: 
cmp %rbp,%rsp
je retend46
popq %r12
jmp ret46
retend46: 
ret
.text
