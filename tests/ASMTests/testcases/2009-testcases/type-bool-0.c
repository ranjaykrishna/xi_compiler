#include <stdio.h>

extern int _Iassert_pb(int);

extern int _IboolEq_bbb(int, int);
extern int _IboolNeg_bb(int);
extern int _IboolNEq_bbb(int, int);

static void test(const char* msg, int result) {
    printf("Testing:%s\n", msg);
    _Iassert_pb(result);
}

int _Imain_paai(int** args) {
    test("=1", _IboolEq_bbb(0,0) == 1);
    test("=2", _IboolEq_bbb(0,1) == 0);
    test("=3", _IboolEq_bbb(1,0) == 0);
    test("=4", _IboolEq_bbb(1,1) == 1);

    test("!=1", _IboolNEq_bbb(0,0) == 0);
    test("!=2", _IboolNEq_bbb(0,1) == 1);
    test("!=3", _IboolNEq_bbb(1,0) == 1);
    test("!=4", _IboolNEq_bbb(1,1) == 0);

    test("!1", _IboolNeg_bb(0) == 1);
    test("!2", _IboolNeg_bb(1) == 0);
    return 0;
}
