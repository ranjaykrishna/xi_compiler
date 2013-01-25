.text
.globl _IprintNum_pi
_IprintNum_pi: 
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
movq $24,%r12
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
movq $2,%r12
pushq %r12
movq -16(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq %rdi,%r12
pushq %r12
movq $48,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
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
movq $46,%r12
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
call _Iprintln_pai
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
popq %r12
_block102End_0:
_block1End_1:
popq %r13
ret2: 
cmp %rbp,%rsp
je retend2
popq %r12
jmp ret2
retend2: 
ret
.globl _IbubbleSort_aiai
_IbubbleSort_aiai: 
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
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
w_head2: 
movq -24(%rbp),%r12
pushq %r12
movq %rdi,%r12
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
subq %r13,%r12
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
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq -24(%rbp),%r12
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
jg _op4
movq $0,%r12
jmp _end4
_op4: 
movq $1,%r12
_end4: 
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
js _op5
movq $0,%r12
jmp _end5
_op5: 
movq $1,%r12
_end5: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds7
f_outOfBounds6: 
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
jnc rspJmp6
movq $1,%r12
jmp rspend6
rspJmp6: 
movq $0,%r12
rspend6: 
bt $3,%rbp
jnc rbpJmp6
movq $1,%r13
jmp rbpend6
rbpJmp6: 
movq $0,%r13
rbpend6: 
test %r12,%r13
jnp extraPush6
pushq $0
movq $1,%rbx
extraPush6: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop6
popq %r13
extraPop6: 
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
t_inBounds7: 
jmp b_lookup8
b_lookup8: 
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
movq %r13,-8(%rbp)
_block11End_2:
popq %r13
popq %r13
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
w_head12: 
movq -16(%rbp),%r12
pushq %r12
movq %rdi,%r12
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
popq %r12
btc $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true13
w_false14: 
pushq $0
movq -16(%rbp),%r12
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
jg _op8
movq $0,%r12
jmp _end8
_op8: 
movq $1,%r12
_end8: 
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
js _op9
movq $0,%r12
jmp _end9
_op9: 
movq $1,%r12
_end9: 
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
jnc rspJmp10
movq $1,%r12
jmp rspend10
rspJmp10: 
movq $0,%r12
rspend10: 
bt $3,%rbp
jnc rbpJmp10
movq $1,%r13
jmp rbpend10
rbpJmp10: 
movq $0,%r13
rbpend10: 
test %r12,%r13
jnp extraPush10
pushq $0
movq $1,%rbx
extraPush10: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop10
popq %r13
extraPop10: 
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
movq -8(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op11
movq $0,%r12
jmp _end11
_op11: 
movq $1,%r12
_end11: 
pushq %r12
popq %r13
movq %r13,-56(%rbp)
_block33End_2:
popq %r13
popq %r13
movq -56(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je if_true23
if_false24: 
jmp if_end25
if_true23: 
pushq $0
movq -16(%rbp),%r12
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
jg _op12
movq $0,%r12
jmp _end12
_op12: 
movq $1,%r12
_end12: 
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
js _op13
movq $0,%r12
jmp _end13
_op13: 
movq $1,%r12
_end13: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds17
f_outOfBounds16: 
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
jnc rspJmp14
movq $1,%r12
jmp rspend14
rspJmp14: 
movq $0,%r12
rspend14: 
bt $3,%rbp
jnc rbpJmp14
movq $1,%r13
jmp rbpend14
rbpJmp14: 
movq $0,%r13
rbpend14: 
test %r12,%r13
jnp extraPush14
pushq $0
movq $1,%rbx
extraPush14: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop14
popq %r13
extraPop14: 
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
t_inBounds17: 
jmp b_lookup18
b_lookup18: 
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
movq %r13,-8(%rbp)
_block21End_2:
popq %r13
popq %r13
movq -16(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
_block22End_0:
jmp if_end25
if_end25: 
_block34End_1:
popq %r13
movq -16(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
_block35End_0:
jmp w_head12
w_true13: 
pushq $0
movq -32(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jnz _op15
movq $0,%r12
jmp _end15
_op15: 
movq $1,%r12
_end15: 
pushq %r12
popq %r13
movq %r13,-40(%rbp)
movq -40(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je if_true65
if_false66: 
jmp if_end67
if_true65: 
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-48(%rbp)
pushq $0
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
movq -56(%rbp),%r12
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
je t_inBounds38
f_outOfBounds37: 
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
t_inBounds38: 
jmp b_lookup39
b_lookup39: 
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
popq %r13
movq %r13,-48(%rbp)
_block42End_2:
popq %r13
popq %r13
pushq $0
movq -24(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
movq -56(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op19
movq $0,%r12
jmp _end19
_op19: 
movq $1,%r12
_end19: 
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
js _op20
movq $0,%r12
jmp _end20
_op20: 
movq $1,%r12
_end20: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds52
f_outOfBounds51: 
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
jnc rspJmp21
movq $1,%r12
jmp rspend21
rspJmp21: 
movq $0,%r12
rspend21: 
bt $3,%rbp
jnc rbpJmp21
movq $1,%r13
jmp rbpend21
rbpJmp21: 
movq $0,%r13
rbpend21: 
test %r12,%r13
jnp extraPush21
pushq $0
movq $1,%rbx
extraPush21: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop21
popq %r13
extraPop21: 
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
t_inBounds52: 
jmp b_lookup53
b_lookup53: 
pushq $0
movq -32(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-72(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-80(%rbp)
movq -72(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op22
movq $0,%r12
jmp _end22
_op22: 
movq $1,%r12
_end22: 
pushq %r12
movq -72(%rbp),%r12
pushq %r12
movq -80(%rbp),%r12
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
js _op23
movq $0,%r12
jmp _end23
_op23: 
movq $1,%r12
_end23: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds45
f_outOfBounds44: 
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
jnc rspJmp24
movq $1,%r12
jmp rspend24
rspJmp24: 
movq $0,%r12
rspend24: 
bt $3,%rbp
jnc rbpJmp24
movq $1,%r13
jmp rbpend24
rbpJmp24: 
movq $0,%r13
rbpend24: 
test %r12,%r13
jnp extraPush24
pushq $0
movq $1,%rbx
extraPush24: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop24
popq %r13
extraPop24: 
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
t_inBounds45: 
jmp b_lookup46
b_lookup46: 
movq -80(%rbp),%r12
pushq %r12
movq -72(%rbp),%r12
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
popq %r13
popq %r12
movq %r12,(%r13)
_block56End_2:
popq %r13
popq %r13
_block49End_2:
popq %r13
popq %r13
pushq $0
movq -32(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-56(%rbp)
pushq $0
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-64(%rbp)
movq -56(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op25
movq $0,%r12
jmp _end25
_op25: 
movq $1,%r12
_end25: 
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
js _op26
movq $0,%r12
jmp _end26
_op26: 
movq $1,%r12
_end26: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds59
f_outOfBounds58: 
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
jnc rspJmp27
movq $1,%r12
jmp rspend27
rspJmp27: 
movq $0,%r12
rspend27: 
bt $3,%rbp
jnc rbpJmp27
movq $1,%r13
jmp rbpend27
rbpJmp27: 
movq $0,%r13
rbpend27: 
test %r12,%r13
jnp extraPush27
pushq $0
movq $1,%rbx
extraPush27: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop27
popq %r13
extraPop27: 
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
t_inBounds59: 
jmp b_lookup60
b_lookup60: 
movq -48(%rbp),%r12
pushq %r12
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
popq %r13
popq %r12
movq %r12,(%r13)
_block63End_2:
popq %r13
popq %r13
_block64End_1:
popq %r13
jmp if_end67
if_end67: 
_block69End_1:
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
_block70End_0:
jmp w_head2
w_true3: 
movq %rdi,%r12
pushq %r12
popq %rax
ret28: 
cmp %rbp,%rsp
je retend28
popq %r12
jmp ret28
retend28: 
ret
_block71End_4:
popq %r13
popq %r13
popq %r13
popq %r13
.globl _Imain_paai
_Imain_paai: 
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
movq $40,%r12
pushq %r12
popq %r13
movq %r13,%rdi
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
call _I_alloc_i
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
popq %r13
movq %r13,-24(%rbp)
movq $4,%r12
pushq %r12
movq -24(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $6,%r12
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
movq $3,%r12
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
movq $4,%r12
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
movq $7,%r12
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
movq -24(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
_block72End_1:
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
jnc rspJmp30
movq $1,%r12
jmp rspend30
rspJmp30: 
movq $0,%r12
rspend30: 
bt $3,%rbp
jnc rbpJmp30
movq $1,%r13
jmp rbpend30
rbpJmp30: 
movq $0,%r13
rbpend30: 
test %r12,%r13
jnp extraPush30
pushq $0
movq $1,%rbx
extraPush30: 
call _IbubbleSort_aiai
cmp $1,%rbx
jne extraPop30
popq %r13
extraPop30: 
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
movq %r13,-8(%rbp)
_block112_End_1:
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
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
movq -24(%rbp),%r12
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
_block103End_0:
t_inBounds75: 
jmp b_lookup76
b_lookup76: 
movq -32(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
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
movq %r13,-16(%rbp)
_block79End_2:
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
jnc rspJmp34
movq $1,%r12
jmp rspend34
rspJmp34: 
movq $0,%r12
rspend34: 
bt $3,%rbp
jnc rbpJmp34
movq $1,%r13
jmp rbpend34
rbpJmp34: 
movq $0,%r13
rbpend34: 
test %r12,%r13
jnp extraPush34
pushq $0
movq $1,%rbx
extraPush34: 
call _IprintNum_pi
cmp $1,%rbx
jne extraPop34
popq %r13
extraPop34: 
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
_block104End_0:
_block114_End_1:
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq $1,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
movq -24(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op35
movq $0,%r12
jmp _end35
_op35: 
movq $1,%r12
_end35: 
pushq %r12
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
popq %r12
pushq (%r12)
popq %r13
popq %r12
cmp %r13,%r12
js _op36
movq $0,%r12
jmp _end36
_op36: 
movq $1,%r12
_end36: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds82
f_outOfBounds81: 
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
jnc rspJmp37
movq $1,%r12
jmp rspend37
rspJmp37: 
movq $0,%r12
rspend37: 
bt $3,%rbp
jnc rbpJmp37
movq $1,%r13
jmp rbpend37
rbpJmp37: 
movq $0,%r13
rbpend37: 
test %r12,%r13
jnp extraPush37
pushq $0
movq $1,%rbx
extraPush37: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop37
popq %r13
extraPop37: 
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
_block105End_0:
t_inBounds82: 
jmp b_lookup83
b_lookup83: 
movq -32(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
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
movq %r13,-16(%rbp)
_block86End_2:
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
jnc rspJmp38
movq $1,%r12
jmp rspend38
rspJmp38: 
movq $0,%r12
rspend38: 
bt $3,%rbp
jnc rbpJmp38
movq $1,%r13
jmp rbpend38
rbpJmp38: 
movq $0,%r13
rbpend38: 
test %r12,%r13
jnp extraPush38
pushq $0
movq $1,%rbx
extraPush38: 
call _IprintNum_pi
cmp $1,%rbx
jne extraPop38
popq %r13
extraPop38: 
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
_block106End_0:
_block116_End_1:
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq $2,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
movq -24(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op39
movq $0,%r12
jmp _end39
_op39: 
movq $1,%r12
_end39: 
pushq %r12
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
popq %r12
pushq (%r12)
popq %r13
popq %r12
cmp %r13,%r12
js _op40
movq $0,%r12
jmp _end40
_op40: 
movq $1,%r12
_end40: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds89
f_outOfBounds88: 
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
jnc rspJmp41
movq $1,%r12
jmp rspend41
rspJmp41: 
movq $0,%r12
rspend41: 
bt $3,%rbp
jnc rbpJmp41
movq $1,%r13
jmp rbpend41
rbpJmp41: 
movq $0,%r13
rbpend41: 
test %r12,%r13
jnp extraPush41
pushq $0
movq $1,%rbx
extraPush41: 
call _I_outOfBounds_p
cmp $1,%rbx
jne extraPop41
popq %r13
extraPop41: 
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
_block107End_0:
t_inBounds89: 
jmp b_lookup90
b_lookup90: 
movq -32(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
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
movq %r13,-16(%rbp)
_block93End_2:
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
jnc rspJmp42
movq $1,%r12
jmp rspend42
rspJmp42: 
movq $0,%r12
rspend42: 
bt $3,%rbp
jnc rbpJmp42
movq $1,%r13
jmp rbpend42
rbpJmp42: 
movq $0,%r13
rbpend42: 
test %r12,%r13
jnp extraPush42
pushq $0
movq $1,%rbx
extraPush42: 
call _IprintNum_pi
cmp $1,%rbx
jne extraPop42
popq %r13
extraPop42: 
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
_block108End_0:
_block118_End_1:
popq %r13
pushq $0
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-16(%rbp)
pushq $0
movq $3,%r12
pushq %r12
popq %r13
movq %r13,-24(%rbp)
pushq $0
movq -8(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-32(%rbp)
movq -24(%rbp),%r12
pushq %r12
movq $-1,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
jg _op43
movq $0,%r12
jmp _end43
_op43: 
movq $1,%r12
_end43: 
pushq %r12
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
popq %r12
pushq (%r12)
popq %r13
popq %r12
cmp %r13,%r12
js _op44
movq $0,%r12
jmp _end44
_op44: 
movq $1,%r12
_end44: 
pushq %r12
popq %r13
popq %r12
and %r13,%r12
pushq %r12
popq %r12
cmp $1,%r12
je t_inBounds96
f_outOfBounds95: 
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
call _I_outOfBounds_p
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
_block109End_0:
t_inBounds96: 
jmp b_lookup97
b_lookup97: 
movq -32(%rbp),%r12
pushq %r12
movq -24(%rbp),%r12
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
movq %r13,-16(%rbp)
_block100End_2:
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
jnc rspJmp46
movq $1,%r12
jmp rspend46
rspJmp46: 
movq $0,%r12
rspend46: 
bt $3,%rbp
jnc rbpJmp46
movq $1,%r13
jmp rbpend46
rbpJmp46: 
movq $0,%r13
rbpend46: 
test %r12,%r13
jnp extraPush46
pushq $0
movq $1,%rbx
extraPush46: 
call _IprintNum_pi
cmp $1,%rbx
jne extraPop46
popq %r13
extraPop46: 
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
_block110End_0:
_block120_End_1:
popq %r13
_block101End_1:
popq %r13
ret47: 
cmp %rbp,%rsp
je retend47
popq %r12
jmp ret47
retend47: 
ret
.text
