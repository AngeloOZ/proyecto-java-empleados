package org.crud.helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortennobel.imagescaling.ResampleOp;

public class ImageScaling {

    private static final Logger logger = LoggerFactory.getLogger(ImageScaling.class);

    private int scaleFactor;

    public ImageScaling(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public byte[] scaleImage(final byte[] binaryData) throws IOException {
        if (binaryData == null) {
            throw new IOException("Empty Data...");
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(binaryData);
        final BufferedImage bufferedImage = ImageIO.read(bais);
        final int imageType = bufferedImage.getType();
        final Point point = this.getScalingFactor(bufferedImage);
        if (point == null) {
            return null;
        }
        byte[] imageData = null;
        try {
            final BufferedImage resizedImage = new BufferedImage((int) point.getX(), (int) point.getY(), imageType);
            new ResampleOp((int) point.getX(), (int) point.getY()).filter(bufferedImage, resizedImage);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos);
            imageData = baos.toByteArray();
        } catch (final Exception ex) {
            logger.error(ex.getMessage(), ex);
            imageData = this.imageScale(binaryData);
        }
        return imageData;
    }

    public byte[] imageScale(final byte[] binaryData) throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(binaryData);
        final BufferedImage bufferedImage = ImageIO.read(bis);
        final Point point = this.getScalingFactor(bufferedImage);
        if (point == null) {
            return null;
        }
        final Image resizedImage = bufferedImage.getScaledInstance((int) point.getX(), (int) point.getY(),
                Image.SCALE_FAST);
        final BufferedImage reescaled = new BufferedImage(resizedImage.getWidth(null), resizedImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = reescaled.createGraphics();
        graphics.drawImage(resizedImage, 0, 0, null);
        graphics.dispose();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(reescaled, "png", baos);
        final byte[] imageData = baos.toByteArray();
        return imageData;
    }

    private Point getScalingFactor(final BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return null;
        }
        final Point point = new Point();
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();
        if (width > height) {
            final double scale = (double) width / scaleFactor;
            final double height2 = height / scale;
            point.setLocation(scaleFactor, height2);
        } else {
            final double scale = (double) height / scaleFactor;
            final double width2 = width / scale;
            point.setLocation(width2, scaleFactor);
        }
        return point;
    }

}
