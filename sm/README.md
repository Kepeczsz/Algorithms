# SM - Signal and Multimedia Processing

This folder contains signal and multimedia processing algorithms from the [Kepeczsz/sm](https://github.com/Kepeczsz/sm) repository.

## Contents

### lab07.py - Audio/Signal Compression

This module implements signal compression techniques commonly used in telecommunications and audio processing:

- **A-law Compression/Decompression** (`aLawComp`, `aLawDecomp`): Implementation of the A-law algorithm (ITU-T G.711 standard) used in European telephone systems for audio signal companding.
  
- **μ-law (Mu-law) Compression/Decompression** (`muLawComp`, `muLawDecomp`): Implementation of the μ-law algorithm (ITU-T G.711 standard) used in North American and Japanese telephone systems.

- **Quantization** (`changeSize`): Function to quantize signals to a specific bit resolution (2-32 bits).

- **DPCM Compression** (`DPCM_compress`): Differential Pulse Code Modulation compression, which encodes the difference between consecutive samples rather than absolute values.

- **Chroma Subsampling** (`chromaSubsampling`, `chromaResampling`): Functions for reducing chrominance information in images/video (e.g., 4:2:2 subsampling used in video compression).

**Dependencies:** numpy, matplotlib

### lab08.py - Image Compression (JPEG-like)

This module implements DCT-based image compression techniques similar to the JPEG standard:

- **DataContainer**: Class to store compressed image data including Y, Cb, Cr channels, quantization matrices, and chroma subsampling ratio.

- **DCT Block Compression** (`CompressBlock`): Applies 2D Discrete Cosine Transform (DCT) to 8x8 blocks, quantizes the result, and applies zigzag scanning.

- **DCT Block Decompression** (`DecompressBlock`): Reverses the compression process using inverse DCT.

- **Color Space Conversion** (`ConversionToYCrCb`, `ConversionToRGB`): Functions to convert between RGB and YCbCr color spaces.

- **Layer Compression** (`CompressLayer`, `DecompressLayer`): Functions to compress/decompress entire image layers by processing 8x8 blocks.

- **Zigzag Scanning** (`zigzag`): Implements the zigzag pattern scanning used in JPEG to convert 8x8 blocks to 1D vectors and vice versa.

**Dependencies:** numpy, scipy, cv2 (OpenCV)

## Usage

These files are educational implementations demonstrating fundamental concepts in signal and image compression. They can be used as a reference for understanding:

- Audio companding techniques (A-law, μ-law)
- Differential encoding (DPCM)
- JPEG-like image compression pipeline
- DCT and quantization in image processing
- Chroma subsampling in video/image compression
