#ViewPager Indicator for Android in Kotlin
Create custom indicator for your viewpagers

##Installing
For now, copy the class in your project.

##How to use:
* Create a custom class extending the abstract class ViewPagerIndicatorView and implements the behaviors "selected" and "unselected"
```
class MyCustomIndicatorView(context: Context) : ViewPagerIndicatorView(context) {

    init {
        LayoutInflater.from(context).inflate(..., this, true)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun select() = ...

    override fun unselect() = ...

}
```
* Include the ViewPagerIndicator in your layout and include in ViewPagerIndicator xml tag the attribute indicatorClass point to your custom class
```
<android.support.constraint.ConstraintLayout
    ...
>

    <android.support.v4.view.ViewPager
        ...
    />

    <br.com.ricardomorsch.viewpagerindicator.ViewPagerIndicator
        ...
        app:indicatorClass="br.com.ricardomorsch.viewpagerindicatorsample.MyCustomIndicatorView"
    />

</android.support.constraint.ConstraintLayout>
```

* Attach the viewpager to the indicator
```
  viewpager.adapter = SampleAdapter(supportFragmentManager)
  indicator.attachViewPager(viewpager)

```
