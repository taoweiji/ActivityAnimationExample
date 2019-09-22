
# Android动画六：Interpolator 实现匀速、加速、减速、抛物线速率等效果

Interpolator 是用于控制动画变化速率，可以让动画效果更加自然好看，比如实现实现匀速、加速、减速、抛物线速率等效果，Android 本身就已经自带了多种效果。
#### 对照表
|类名|效果|
|-|-|
|AccelerateDecelerateInterpolator|[先加速后减速](https://easings.net/en#easeInOutQuart)
|AccelerateInterpolator|[一直加速](https://easings.net/en#easeInQuint)|
|AnticipateInterpolator|[先回退一小步然后加速前进](https://easings.net/en#easeInBack)|
|AnticipateOvershootInterpolator|在上一个基础上超出终点一小步再回到终点|
|BounceInterpolator|[结束后弹跳一会](https://easings.net/en#easeOutBack)|
|CycleInterpolator|周期运动|
|DecelerateInterpolator|[减速](https://easings.net/en#easeOutQuart)|
|LinearInterpolator|匀速|
|OvershootInterpolator|[快速到达终点并超出一小步最后回到终点](https://easings.net/en#easeOutBack)|
|PathInterpolator|指定路径速度|
可以考虑收藏该篇文章，后续方便查找效果对应。

#### 效果展示
[https://easings.net/en](https://easings.net/en)

#### 使用场景
1. 普通 Activity 过渡动画
2. 5.0 版本 Activity 过渡动画
3. Animator 动画
5. Transition 动画
6. ProgressBar 动画
6. RecyclerView 动画
#### 示例代码



[https://blog.csdn.net/pzm1993/article/details/77926373](https://blog.csdn.net/pzm1993/article/details/77926373)

[https://my.oschina.net/huangzhi1bo/blog/857192](https://my.oschina.net/huangzhi1bo/blog/857192)
