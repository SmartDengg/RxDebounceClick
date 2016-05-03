#RxDebounceClick
[![](https://jitpack.io/v/SmartDengg/RxDebounceClick.svg)](https://jitpack.io/#SmartDengg/RxDebounceClick)

![](./images/launcher.png)

Sketch
--------
It's similar to the Butterknife's [DebouncingOnClickListener](https://github.com/JakeWharton/butterknife/blob/master/butterknife/src/main/java/butterknife/internal/DebouncingOnClickListener.java). That also means "A click listener that debounces multiple clicks posted in the same frame. A click on one button disables all buttons for that frame."  

This library use [RxJava](https://github.com/ReactiveX/RxJava) but not support [Backpressure](https://github.com/ReactiveX/RxJava/wiki/Backpressure).


Usage
--------

- Gradle:

**Step 1.** Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

**Step 2.** Add the dependency

```java
dependencies {
    compile 'com.github.SmartDengg:RxDebounceClick:0.0.1' 
}
```

- Java

```java
RxDebounceClick.onClick(button)
               .subscribe(new Action1<Void>() {
                   @Override
                   public void call(Void aVoid) {
                       // Do stuff
                   }
               });
```

Preview
--------

![](./images/rxdebounceclick.gif)


Developed By
--------
- SmartDengg - Hi4Joker@gmail.com

[SmartDengg's blog](http://www.jianshu.com/users/df40282480b4/latest_articles)
 
[SmartDengg's imooc community](http://www.imooc.com/myclub/article/uid/2536335)

<a href="http://weibo.com/5367097592/profile?rightmod=1&wvr=6&mod=personinfo">
  <img alt="Follow me on Weibo" src="http://upload-images.jianshu.io/upload_images/268450-50e41e15ac29b776.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" />
</a>

License
--------

	Copyright (c) 2016 小鄧子

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.

    














