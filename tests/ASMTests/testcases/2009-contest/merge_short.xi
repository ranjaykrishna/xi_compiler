use assert
use io
use conv

main(args:int[][]) {a:int[]=(20,19,18,14,2,3,6,5,1,15,16,11,13,12,7,8,9,17,10,4
) b:int[]=(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20) merge(a,0,19) 
assertEqual(a,b) }merge(a:int[],l:int,h:int) {if(l>=h) {return} m:int=(h+l)/2 
merge(a,l,m) merge(a,m+1,h) r:int[h-l+1] i:int=0 i1:int=l i2:int=m+1 
while(i1<=m&i2<=h){if(a[i1]<=a[i2]){r[i]=a[i1] i1=i1+1 }else{r[i]=a[i2] i2=i2+1 }
i=i+1 }while(i1<=m){r[i]=a[i1] i1=i1+1 i=i+1 }while(i2<=h){r[i]=a[i2] i2=i2+1 
i=i+1 }i=0 while(l<=h){a[l]=r[i] i=i+1 l=l+1 }}assertEqual(a1:int[],a2:int[])
{i:int=0 n:int=length(a1) while(i<n){assert(a1[i]==a2[i]) i=i+1 }}

// For your sanity, since comments don't count against the the 10 lines...
//
//main(args:int[][]) {
//  a:int[]=(20,19,18,14,2,3,6,5,1,15,16,11,13,12,7,8,9,17,10,4) 
//  b:int[]=(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20) 
//  merge(a,0,19) 
//  assertEqual(a,b) 
//}

//merge(a:int[],l:int,h:int) {
//  print("initially h,l =")
//  print(unparseInt(h))
//  print(",")
//  println(unparseInt(l))
//  if(l>=h) { return }
//  m:int=(h+l)/2 
//  merge(a,l,m) 
//  merge(a,m+1,h) 
//  r:int[h-l+1] 
//  print("h,l = ")
//  print(unparseInt(h))
//  print(",")
  //println(unparseInt(l))
  //print("h-l+1=")
  //println(unparseInt(h-l+1))
  //i:int=0 
  //i1:int=l 
  //i2:int=m+1 
  //println("before first while")
  //while(i1<=m&i2<=h){
//    if(a[i1]<=a[i2]){
      //r[i]=a[i1] i1=i1+1 
    //}else{
//      r[i]=a[i2] i2=i2+1 
    //}
    //i=i+1 
  //}
  ///print("before second while, m=")
  //println(unparseInt(m))
  //print("length of r is ")
  //println(unparseInt(length(r)))
  //print("length of a is ")
  //println(unparseInt(length(a)))
  //while(i1<=m){
//    print("i=")
    //println(unparseInt(i))
    //print("i1=")
    //println(unparseInt(i1))
    //r[i]=a[i1] 
    //i1=i1+1 
    //i=i+1 
  //}
  //println("before third while")
  //while(i2<=h){
//    r[i]=a[i2] 
    //i2=i2+1 
    //i=i+1 
  //}
  //i=0 
  //println("before fourth while")
  //while(l<=h){
//    a[l]=r[i] 
    //i=i+1 
    //l=l+1 
  //}
  //println("after fourth while")
  //return
//}
//
//assertEqual(a1:int[],a2:int[]) {
  //i:int=0 
  //n:int=length(a1) 
  //while(i<n){
  //  assert((a1[i])==(a2[i])) 
  //  i=i+1 
  //}
//}

