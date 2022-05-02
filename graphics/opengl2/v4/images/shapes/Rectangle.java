package com.martinmimiGames.util.graphics.opengl2.v4.images.shapes;

import android.content.Context;
import android.graphics.Bitmap;

import com.martinmimiGames.util.graphics.opengl2.v4.Draw;
import com.martinmimiGames.util.graphics.opengl2.v4.Images;

/**
 * This is the MGGames utility dependency.
 * Rectangle in Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Rectangle extends Images {

  /**
   * @param context  the context with the screen
   * @param imageRId the id in R.java
   */
  public Rectangle(Context context, int imageRId) {
    super(context, imageRId);
    init();
  }

  /**
   * @param bitmap the bitmap with the texture
   */
  public Rectangle(Bitmap bitmap) {
    super(bitmap);
    init();
  }

  private void init() {
    this.points = 4;
    this.updateStride();
    this.vertex_data = new float[this.stride];
    setWidth(1080f);
    setHeight(1080f);
    setImageCrop(0f, 1f, 0f, 1f);
  }

  public static final class POINTS {

    static final int NULL = 0;
    static final int MINUS_HALF_WIDTH = 1;
    static final int MINUS_HALF_HEIGHT = 2;
    static final int PLUS_HALF_WIDTH = 3;
    static final int PLUS_HALF_HEIGHT = 4;

    public static final int[] VALUE =
        {
            MINUS_HALF_WIDTH,
            MINUS_HALF_HEIGHT,
            NULL,
            NULL,

            MINUS_HALF_WIDTH,
            PLUS_HALF_HEIGHT,
            NULL,
            NULL,

            PLUS_HALF_WIDTH,
            MINUS_HALF_HEIGHT,
            NULL,
            NULL,

            PLUS_HALF_WIDTH,
            PLUS_HALF_HEIGHT,
            NULL,
            NULL
        };

  }

  public static final class Crop {

    static final int NULL = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int TOP = 3;
    static final int BOTTOM = 4;

    public static final int[] VALUE =
        {
            NULL,
            NULL,
            LEFT,
            BOTTOM,

            NULL,
            NULL,
            LEFT,
            TOP,

            NULL,
            NULL,
            RIGHT,
            BOTTOM,

            NULL,
            NULL,
            RIGHT,
            TOP
        };

  }

  /**
   * crop the image,
   * value 0 - 1
   *
   * @param left   value 0 (left) - 1 (right),
   *               default = 0
   * @param right  value 0 (left) - 1 (right),
   *               default = 1
   * @param top    value 0 (top) - 1 (bottom),
   *               default = 0;
   * @param bottom value 0 (top) - 1 (bottom),
   *               default = 1;
   * @return this Rectangle
   */
  public Rectangle setImageCrop(final float left,
                                final float right,
                                final float top,
                                final float bottom
  ) {
    for (int i = 0; i < this.vertex_data.length; i++) {
      switch (Crop.VALUE[i]) {
        case Crop.LEFT:
          this.vertex_data[i] = left;
          break;
        case Crop.RIGHT:
          this.vertex_data[i] = right;
          break;
        case Crop.TOP:
          this.vertex_data[i] = top;
          break;
        case Crop.BOTTOM:
          this.vertex_data[i] = bottom;
          break;
        case Crop.NULL:
        default:
          break;

      }
    }
    return this;
  }

  /**
   * set object width
   *
   * @param width pixel value
   * @return this Rectangle
   */
  public Rectangle setWidth(final float width) {
    final float half_width = width / Draw.height;
    for (int i = 0; i < this.vertex_data.length; i++) {
      switch (POINTS.VALUE[i]) {
        case POINTS.MINUS_HALF_WIDTH:
          this.vertex_data[i] = -half_width;
          break;
        case POINTS.PLUS_HALF_WIDTH:
          this.vertex_data[i] = half_width;
          break;
        default:
          break;
      }
    }
    return this;
  }

  /**
   * set object height
   *
   * @param height pixel value
   * @return this Rectangle
   */
  public Rectangle setHeight(final float height) {
    final float half_height = height / Draw.height;
    for (int i = 0; i < this.vertex_data.length; i++) {
      switch (POINTS.VALUE[i]) {
        case POINTS.MINUS_HALF_HEIGHT:
          this.vertex_data[i] = -half_height;
          break;
        case POINTS.PLUS_HALF_HEIGHT:
          this.vertex_data[i] = half_height;
          break;
        default:
          break;
      }
    }
    return this;
  }

}
