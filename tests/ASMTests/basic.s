.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $152,%rsp
movq $-4,%r12
pushq %r12
popq %r13
movq %r13,-88(%rbp)
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
movq -88(%rbp),%r12
pushq %r12
movq $2,%r12
pushq %r12
popq %r13
movq %r13,%rsi
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
call _Ifunc_iii
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
movq $24,%r12
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
movq $2,%r12
pushq %r12
movq -104(%rbp),%r12
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
movq $98,%r12
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
movq -104(%rbp),%r12
pushq %r12
movq $16,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-120(%rbp)
movq $58,%r12
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
movq -112(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-128(%rbp)
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
movq -128(%rbp),%r12
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
call _Iprint_pai
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
jnc rspJmp3
movq $1,%r12
jmp rspend3
rspJmp3: 
movq $0,%r12
rspend3: 
bt $3,%rbp
jnc rbpJmp3
movq $1,%r13
jmp rbpend3
rbpJmp3: 
movq $0,%r13
rbpend3: 
test %r12,%r13
jnp extraPush3
pushq $0
movq $1,%rbx
extraPush3: 
call _IunparseInt_aii
cmp $1,%rbx
jne extraPop3
popq %r13
extraPop3: 
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
movq -136(%rbp),%r12
pushq %r12
popq %r13
movq %r13,%rdi
movq $0,%rbx
bt $3,%rsp
jnc rspJmp4
movq $1,%r12
jmp rspend4
rspJmp4: 
movq $0,%r12
rspend4: 
bt $3,%rbp
jnc rbpJmp4
movq $1,%r13
jmp rbpend4
rbpJmp4: 
movq $0,%r13
rbpend4: 
test %r12,%r13
jnp extraPush4
pushq $0
movq $1,%rbx
extraPush4: 
call _Iprintln_pai
cmp $1,%rbx
jne extraPop4
popq %r13
extraPop4: 
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
ret5: 
cmp %rbp,%rsp
je retend5
popq %r12
jmp ret5
retend5: 
ret
.globl _Ifunc_iii
_Ifunc_iii: 
movq %rsp,%rbp
sub $136,%rsp
movq %rdi,%r12
pushq %r12
movq $0,%r12
pushq %r12
popq %r13
popq %r12
cmp %r13,%r12
js _op6
movq $0,%r12
jmp _end6
_op6: 
movq $1,%r12
_end6: 
pushq %r12
popq %r13
movq %r13,-88(%rbp)
movq -88(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-96(%rbp)
movq -96(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je if_true3
if_false4: 
movq %rdi,%r12
pushq %r12
movq %rsi,%r12
pushq %r12
popq %r13
popq %r12
imul %r13,%r12
pushq %r12
popq %r13
movq %r13,-104(%rbp)
movq -104(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-112(%rbp)
movq -112(%rbp),%r12
pushq %r12
popq %rax
ret7: 
cmp %rbp,%rsp
je retend7
popq %r12
jmp ret7
retend7: 
ret
if_true3: 
movq %rdi,%r12
pushq %r12
movq %rsi,%r12
pushq %r12
movq %rdx,%r14
popq %r13
popq %r12
movq %r12,%rax
cmp $0,%r12
jl div8
movq $0,%rdx
jmp divEnd8
div8:
movq $-1,%rdx
divEnd8:
idiv %r13
movq %r14,%rdx
pushq %rax
popq %r13
movq %r13,-120(%rbp)
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
movq %rdi,%r12
pushq %r12
movq %rsi,%r12
pushq %r12
movq %rdx,%r14
popq %r13
popq %r12
movq %r12,%rax
cmp $0,%r12
jl div9
movq $0,%rdx
jmp divEnd9
div9:
movq $-1,%rdx
divEnd9:
idiv %r13
movq %r14,%rdx
pushq %rax
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
call _Ijunk_ii
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
movq %r13,-128(%rbp)
movq -128(%rbp),%r12
pushq %r12
popq %rax
ret11: 
cmp %rbp,%rsp
je retend11
popq %r12
jmp ret11
retend11: 
ret
.globl _Ijunk_ii
_Ijunk_ii: 
movq %rsp,%rbp
sub $136,%rsp
movq %rdi,%r12
pushq %r12
popq %r13
movq %r13,-88(%rbp)
w_head10: 
movq %rdi,%r12
pushq %r12
movq $0,%r12
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
movq %r13,-96(%rbp)
movq %rdi,%r12
pushq %r12
movq $0,%r12
pushq %r12
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
movq %r13,-104(%rbp)
movq -96(%rbp),%r12
pushq %r12
popq %r13
movq %r13,-112(%rbp)
movq -112(%rbp),%r12
pushq %r12
popq %r12
cmp $1,%r12
je w_true11
w_false12: 
movq %rdi,%r12
pushq %r12
movq $1,%r12
pushq %r12
popq %r13
popq %r12
add %r13,%r12
pushq %r12
popq %r13
movq %r13,-120(%rbp)
jmp w_head10
w_true11: 
movq %rdi,%r12
pushq %r12
popq %rax
ret14: 
cmp %rbp,%rsp
je retend14
popq %r12
jmp ret14
retend14: 
ret
.text
