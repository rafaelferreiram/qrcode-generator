package com.qrcode.generator.barcodes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.generator.request.BarcodeRequestDTO;

public class QRGenBarcodeGenerator {

	public static byte[] getQRCodeImageByteArray(BarcodeRequestDTO request) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(request.format(), BarcodeFormat.QR_CODE, 350, 350);
		Path path = FileSystems.getDefault().getPath("./QR-Codes/" + request.getName()+"_" + new Date() + ".png");

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
		
	}
}
