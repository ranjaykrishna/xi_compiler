.text
.globl _Imain_paai
_Imain_paai: 
movq %rsp,%rbp
sub $96,%rsp
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
movq $15,%r12
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
call _Ifibonacci_ii
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
movq %rax,%r12
movq %r12,-88(%rbp)
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
call _IunparseInt_aii
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
movq %rax,%r12
movq %r12,-96(%rbp)
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
call _Iprintln_pai
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
movq %rax,%r12
ret3: 
cmp %rbp,%rsp
je retend3
popq %r12
jmp ret3
retend3: 
ret
.globl _Ifibonacci_ii
_Ifibonacci_ii: 
movq %rsp,%rbp
sub $128,%rsp
movq %rdi,%r12
movq %r12,%r13
pushq %r13
movq $2,%r12
popq %r13
cmp %r12,%r13
jle _op4
movq $0,%r12
jmp _end4
_op4: 
movq $1,%r12
_end4: 
movq %r12,-88(%rbp)
movq -88(%rbp),%r12
cmp $1,%r12
je if_true2
if_false3: 
movq %rdi,%r12
movq %r12,%r13
pushq %r13
movq $1,%r12
popq %r13
subq %r12,%r13
movq %r13,%r12
movq %r12,-96(%rbp)
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
call _Ifibonacci_ii
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
movq %rax,%r12
movq %r12,-104(%rbp)
movq %rdi,%r12
movq %r12,%r13
pushq %r13
movq $2,%r12
popq %r13
subq %r12,%r13
movq %r13,%r12
movq %r12,-112(%rbp)
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
movq -112(%rbp),%r12
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
call _Ifibonacci_ii
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
movq %rax,%r12
movq %r12,-120(%rbp)
movq -104(%rbp),%r12
movq %r12,%r13
pushq %r13
movq -120(%rbp),%r12
popq %r13
add %r12,%r13
movq %r13,%r12
movq %r12,-128(%rbp)
movq -128(%rbp),%r12
movq %r12,%rax
ret7: 
cmp %rbp,%rsp
je retend7
popq %r12
jmp ret7
retend7: 
ret
if_true2: 
movq $1,%r12
movq %r12,%rax
ret8: 
cmp %rbp,%rsp
je retend8
popq %r12
jmp ret8
retend8: 
ret
.text
