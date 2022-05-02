package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.translateM;

import android.content.Context;

import com.martinmimiGames.util.graphics.opengl2.v4.images.shapes.Rectangle;
import com.martinmimiGames.util.objects.LocationXYA;

/**
 * This is the MGGames utility dependency.
 * Object for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class Object implements Drawable, Cloneable {

  /**
   * location values on the screen
   */
  public LocationXYA location;
  /**
   * the texture
   */
  public Drawable drawable;

  /**
   * creates an Object,
   * no default Drawable
   */
  public Object() {
    location = new LocationXYA();
  }

  /**
   * create an Object with defaults,
   * best used for textured objects
   *
   * @param draw    the Draw.java
   * @param context the context with the screen
   * @param Rid     the id in R.java
   * @return a new Object with defaults
   */
  public static Object Images(final Context context, final int Rid) {
    final Object object = new Object();
    object.drawable = new Rectangle(context, Rid);
    return object;
  }

  @Override
  public void draw() {

    final float x = location.x * Draw.ratio;
    final float y = location.y * Draw.ratio;

    if (location.angle != 0) {
      // the drawing process
      translateM(Draw.projectionMatrix, 0, x, -y, 0f);
      rotateM(Draw.projectionMatrix, 0, -location.angle, 0f, 0f, 1f);

      drawable.draw();

      rotateM(Draw.projectionMatrix, 0, location.angle, 0f, 0f, 1f);

      translateM(Draw.projectionMatrix, 0, -x, y, 0f);
    } else {
      // the drawing process
      translateM(Draw.projectionMatrix, 0, x, -y, 0f);

      drawable.draw();

      translateM(Draw.projectionMatrix, 0, -x, y, 0f);
    }
    // the end of the drawing process
  }

}
