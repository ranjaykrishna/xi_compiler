.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $328,%rsp
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
movq $3,%r12
pushq %r12
movq -88(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $65,%r12
pushq %r12
movq -88(%rbp),%r12
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
movq -88(%rbp),%r12
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
movq -88(%rbp),%r12
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
movq -88(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
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
movq $56,%r12
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
movq %r13,-104(%rbp)
movq $6,%r12
pushq %r12
movq -104(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $104,%r12
pushq %r12
movq -104(%rbp),%r12
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
movq -104(%rbp),%r12
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
movq -104(%rbp),%r12
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
movq -104(%rbp),%r12
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
movq -104(%rbp),%r12
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
movq $32,%r12
pushq %r12
movq -104(%rbp),%r12
pushq %r12
movq $48,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -104(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-112(%rbp)
movq -96(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-120(%rbp)
movq -112(%rbp),%r12
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
movq %r13,-128(%rbp)
movq -128(%rbp),%r12
pushq %r12
movq -120(%rbp),%r12
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
movq %r13,-136(%rbp)
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
movq $8,%r12
pushq %r12
movq -136(%rbp),%r12
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
movq %r13,-144(%rbp)
movq -136(%rbp),%r12
pushq %r12
movq -144(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -144(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-144(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-152(%rbp)
w_head2: 
movq -152(%rbp),%r12
pushq %r12
movq -128(%rbp),%r12
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
movq -112(%rbp),%r12
pushq %r12
movq -152(%rbp),%r12
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
movq -144(%rbp),%r12
pushq %r12
movq -152(%rbp),%r12
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
movq -152(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-152(%rbp)
jmp w_head2
w_true3: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-160(%rbp)
w_head6: 
movq -152(%rbp),%r12
pushq %r12
movq -136(%rbp),%r12
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
movq -120(%rbp),%r12
pushq %r12
movq -160(%rbp),%r12
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
movq -144(%rbp),%r12
pushq %r12
movq -152(%rbp),%r12
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
movq -152(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-152(%rbp)
movq -160(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-160(%rbp)
jmp w_head6
w_true7: 
movq -144(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-168(%rbp)
movq -168(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-176(%rbp)
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
movq $32,%r12
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
call _I_alloc_i
cmp $1,%rbx
jne extraPop5
popq %r13
extraPop5: 
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
movq %r13,-184(%rbp)
movq $3,%r12
pushq %r12
movq -184(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $70,%r12
pushq %r12
movq -184(%rbp),%r12
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
movq $71,%r12
pushq %r12
movq -184(%rbp),%r12
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
movq $72,%r12
pushq %r12
movq -184(%rbp),%r12
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
movq -184(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-192(%rbp)
movq -176(%rbp),%r12
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
movq %r13,-200(%rbp)
movq -200(%rbp),%r12
pushq %r12
movq -192(%rbp),%r12
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
movq %r13,-208(%rbp)
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
movq $8,%r12
pushq %r12
movq -208(%rbp),%r12
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
call _I_alloc_i
cmp $1,%rbx
jne extraPop6
popq %r13
extraPop6: 
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
movq %r13,-216(%rbp)
movq -208(%rbp),%r12
pushq %r12
movq -216(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -216(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-216(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-224(%rbp)
w_head12: 
movq -224(%rbp),%r12
pushq %r12
movq -200(%rbp),%r12
pushq %r12
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
movq -176(%rbp),%r12
pushq %r12
movq -224(%rbp),%r12
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
movq -216(%rbp),%r12
pushq %r12
movq -224(%rbp),%r12
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
movq -224(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-224(%rbp)
jmp w_head12
w_true13: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-232(%rbp)
w_head16: 
movq -224(%rbp),%r12
pushq %r12
movq -208(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op8
movq $0,%r12
jmp _end8
_op8: 
movq $1,%r12
_end8: 
pushq %r12
popq %r12
btc $0,%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true17
w_false18: 
movq -192(%rbp),%r12
pushq %r12
movq -232(%rbp),%r12
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
movq -216(%rbp),%r12
pushq %r12
movq -224(%rbp),%r12
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
movq -224(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-224(%rbp)
movq -232(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-232(%rbp)
jmp w_head16
w_true17: 
movq -216(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-240(%rbp)
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
movq $32,%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp9
movq $1,%r12
jmp rspend9
rspJmp9: 
movq $0,%r12
rspend9: 
bt $3,%rbp
jnc rbpJmp9
movq $1,%r13
jmp rbpend9
rbpJmp9: 
movq $0,%r13
rbpend9: 
test %r12,%r13
jnp extraPush9
pushq $0
movq $1,%rbx
extraPush9: 
call _I_alloc_i
cmp $1,%rbx
jne extraPop9
popq %r13
extraPop9: 
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
movq %r13,-248(%rbp)
movq $3,%r12
pushq %r12
movq -248(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq $73,%r12
pushq %r12
movq -248(%rbp),%r12
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
movq $74,%r12
pushq %r12
movq -248(%rbp),%r12
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
movq $75,%r12
pushq %r12
movq -248(%rbp),%r12
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
movq -248(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-256(%rbp)
movq -240(%rbp),%r12
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
movq %r13,-264(%rbp)
movq -264(%rbp),%r12
pushq %r12
movq -256(%rbp),%r12
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
movq %r13,-272(%rbp)
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
movq $8,%r12
pushq %r12
movq -272(%rbp),%r12
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
call _I_alloc_i
cmp $1,%rbx
jne extraPop10
popq %r13
extraPop10: 
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
movq %r13,-280(%rbp)
movq -272(%rbp),%r12
pushq %r12
movq -280(%rbp),%r12
pushq %r12
popq %r13
popq %r12
movq %r12,(%r13)
movq -280(%rbp),%r12
pushq %r12
movq $8,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-280(%rbp)
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-288(%rbp)
w_head22: 
movq -288(%rbp),%r12
pushq %r12
movq -264(%rbp),%r12
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
popq %r12
btc $0,%r12
pushq %r12
popq %r13
movq %r13,-296(%rbp)
movq -296(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true23
w_false24: 
movq -240(%rbp),%r12
pushq %r12
movq -288(%rbp),%r12
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
movq -280(%rbp),%r12
pushq %r12
movq -288(%rbp),%r12
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
movq -288(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-288(%rbp)
jmp w_head22
w_true23: 
movq $0,%r12
pushq %r12
popq %r13
movq %r13,-304(%rbp)
w_head26: 
movq -288(%rbp),%r12
pushq %r12
movq -272(%rbp),%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op12
movq $0,%r12
jmp _end12
_op12: 
movq $1,%r12
_end12: 
pushq %r12
popq %r12
btc $0,%r12
pushq %r12
popq %r13
movq %r13,-312(%rbp)
movq -312(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true27
w_false28: 
movq -256(%rbp),%r12
pushq %r12
movq -304(%rbp),%r12
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
movq -280(%rbp),%r12
pushq %r12
movq -288(%rbp),%r12
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
movq -288(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-288(%rbp)
movq -304(%rbp),%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-304(%rbp)
jmp w_head26
w_true27: 
movq -280(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-320(%rbp)
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
movq -320(%rbp),%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp13
movq $1,%r12
jmp rspend13
rspJmp13: 
movq $0,%r12
rspend13: 
bt $3,%rbp
jnc rbpJmp13
movq $1,%r13
jmp rbpend13
rbpJmp13: 
movq $0,%r13
rbpend13: 
test %r12,%r13
jnp extraPush13
pushq $0
movq $1,%rbx
extraPush13: 
call _Iprintln_pai
cmp $1,%rbx
jne extraPop13
popq %r13
extraPop13: 
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
ret14: 
cmp %rbp,%rsp
je retend14
popq %r12
jmp ret14
retend14: 
ret
.text
