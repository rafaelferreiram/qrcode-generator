package com.qrcode.generator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrcode.generator.barcodes.QRGenBarcodeGenerator;
import com.qrcode.generator.request.BarcodeRequestDTO;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

	@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] qrgenQRCodePost(@RequestBody BarcodeRequestDTO request) throws Exception {
		byte[] qrCodeImageByteArray = QRGenBarcodeGenerator.getQRCodeImageByteArray(request);
		return qrCodeImageByteArray;
	}

}
