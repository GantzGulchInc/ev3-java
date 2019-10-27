package com.gantzgulch.lego.platform.common;

import java.io.IOException;
import java.nio.file.Path;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.brickpi.BrickPiPlatform;
import com.gantzgulch.lego.platform.common.device.BoardImpl;
import com.gantzgulch.lego.platform.ev3.EV3Platform;
import com.gantzgulch.lego.util.exception.PlatformRuntimeException;
import com.gantzgulch.lego.util.logger.EV3Logger;

public class PlatformFactory {

    public static final PlatformFactory INSTANCE = new PlatformFactory();

    public static final String EV3_BRICK_MODEL_REGEX = "\\s*LEGO\\s*MINDSTORMS\\s*EV3\\s*";

    public static final String RASPBERRY_PI_MODEL_REGEX = "\\s*Raspberry\\s*Pi.*";

    public static final String BRICKPI3_MODEL_REGEX = ".*BrickPi3.*";

    private static final EV3Logger LOG = EV3Logger.getLogger(PlatformFactory.class);

    private Platform platformInstance = null;

    private PlatformFactory() {

    }

    public synchronized Platform getPlatform() {

        if (platformInstance == null) {
            platformInstance = createPlatform();
        }

        return platformInstance;
    }

    private Platform createPlatform() {

        try (final Board board0 = new BoardImpl(Path.of("/sys/class/board-info/board0"));
                final Board board1 = new BoardImpl(Path.of("/sys/class/board-info/board1"))) {

            if (isEV3Brick(board0, board1)) {
                return new EV3Platform();
            }

            if (isRaspberryBrickPi(board0, board1)) {
                return new BrickPiPlatform();
            }

        } catch (final RuntimeException | IOException e) {
            throw new PlatformRuntimeException("Unexpected exception identifying platform: " + e.getMessage(), e);
        }

        throw new PlatformRuntimeException("Unable to identify platform.");

    }

    private boolean isEV3Brick(final Board board0, final Board board1) {

        LOG.finest("isEV3Brick: enter");

        return boardExistsAndIsModel(board0, EV3_BRICK_MODEL_REGEX);

    }

    private boolean isRaspberryBrickPi(final Board board0, final Board board1) {

        LOG.finest("isRaspberryBrickPi: enter");

        return boardExistsAndIsModel(board0, RASPBERRY_PI_MODEL_REGEX)

                && boardExistsAndIsModel(board1, BRICKPI3_MODEL_REGEX);
    }

    private boolean boardExistsAndIsModel(final Board board, final String regex) {

        LOG.finest("boardExistsAndIsModel: enter");

        try {

            if (board.exists()) {

                final String model = board.getModel();

                LOG.finest("boardExistsAndIsModel: model: %s, regex: %s", model, regex);

                if (model.matches(regex)) {

                    LOG.finest("boardExistsAndIsModel: matches!");

                    return true;
                } else {
                    LOG.finest("boardExistsAndIsModel: no match.");
                }

            }

        } catch (final RuntimeException e) {
            LOG.warning(e, "boardExistsAndIsModel: Unexpected exception: %s", e.getMessage());
        }

        return false;
    }
}
