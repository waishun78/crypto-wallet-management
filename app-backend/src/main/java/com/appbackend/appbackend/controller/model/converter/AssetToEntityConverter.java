//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AssetDto;
//import com.appbackend.appbackend.model.Asset;
//
//import java.util.function.Function;
//
//public class AssetToEntityConverter implements Function<AssetDto, Asset> {
//    @Override
//    public Asset apply(AssetDto asset) {
//        return new Asset(asset.getAssetId(), asset.getAccount().getId(), asset.getCryptoId(), asset.getCryptoName(), asset.getQuantity());
//    }
//}