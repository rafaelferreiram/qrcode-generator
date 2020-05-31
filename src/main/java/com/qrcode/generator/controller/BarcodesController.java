package com.qrcode.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrcode.generator.barcodes.QRGenBarcodeGenerator;
import com.qrcode.generator.dto.request.BarcodeRequestDTO;
import com.qrcode.generator.dto.response.QRCodeResponseErrorDTO;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

	@Autowired
	private QRGenBarcodeGenerator qrcodeGenerator;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity qrgenQRCodePost(@RequestBody BarcodeRequestDTO request) throws Exception {
		byte[] qrCodeImageByteArray = qrcodeGenerator.getQRCodeImageByteArray(request);
		if (qrCodeImageByteArray == null) {
			return ResponseEntity.badRequest().body(new QRCodeResponseErrorDTO("Error", "Could not generate QRCode"));
		}
		return ResponseEntity.ok().body(qrCodeImageByteArray);
	}

}
