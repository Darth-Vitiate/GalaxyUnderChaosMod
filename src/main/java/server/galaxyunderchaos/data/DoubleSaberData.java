package server.galaxyunderchaos.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DoubleSaberData(String leftHilt, String rightHilt, String leftColor, String rightColor) {
    public static final Codec<DoubleSaberData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("leftHilt").forGetter(DoubleSaberData::leftHilt),
            Codec.STRING.fieldOf("rightHilt").forGetter(DoubleSaberData::rightHilt),
            Codec.STRING.fieldOf("leftColor").forGetter(DoubleSaberData::leftColor),
            Codec.STRING.fieldOf("rightColor").forGetter(DoubleSaberData::rightColor)
    ).apply(instance, DoubleSaberData::new));
}
