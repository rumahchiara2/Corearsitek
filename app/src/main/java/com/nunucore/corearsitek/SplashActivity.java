import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

...

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    // Shine untuk Core Arsitek
    ImageView shineArsitek = findViewById(R.id.shineArsitek);
    Animation animArsitek = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
    shineArsitek.startAnimation(animArsitek);

    // Shine untuk Core Studio
    ImageView shineStudio = findViewById(R.id.shineStudio);
    Animation animStudio = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
    shineStudio.startAnimation(animStudio);
}
