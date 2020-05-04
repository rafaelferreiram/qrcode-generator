package com.qrcode.generator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrcode.generator.barcodes.QRGenBarcodeGenerator;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

	@GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] qrgenQRCode() throws Exception {
		String barcode = "Test";
		byte[] qrCodeImageByteArray = QRGenBarcodeGenerator.getQRCodeImageByteArray(barcode);
		return qrCodeImageByteArray;
	}

}
