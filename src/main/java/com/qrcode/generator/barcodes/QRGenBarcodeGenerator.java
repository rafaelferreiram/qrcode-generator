package com.qrcode.generator.barcodes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.generator.dto.request.BarcodeRequestDTO;

@Component
public class QRGenBarcodeGenerator {

	private Logger logger = LoggerFactory.getLogger(QRGenBarcodeGenerator.class);

	private String TYPE = "PNG";

	private String PATH_FOLDER = "./QR-Codes/";

	public byte[] getQRCodeImageByteArray(BarcodeRequestDTO request) throws WriterException, IOException {
		byte[] pngData = null;
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(request.format(), BarcodeFormat.QR_CODE, 350, 350);
			Path path = FileSystems.getDefault().getPath(PATH_FOLDER + request.getName() + "_" + new Date() + ".png");
			logger.info("QRCode generate and saved into " + PATH_FOLDER);

			ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToPath(bitMatrix, TYPE, path);
			MatrixToImageWriter.writeToStream(bitMatrix, TYPE, pngOutputStream);

			pngData = pngOutputStream.toByteArray();
		} catch (Exception e) {
			logger.error("Error while generating QRCODE.", e.getMessage());
		}
		return pngData;

	}
}
