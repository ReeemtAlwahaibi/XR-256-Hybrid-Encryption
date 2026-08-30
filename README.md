# XR-256 — Custom Hybrid Encryption Algorithm

A hybrid symmetric encryption algorithm built in Java that combines **XOR ciphering**, **bit rotation**, and **SHA-256 hashing** to provide confidentiality and data integrity. Developed as a course project for Information Security.

## Overview

XR-256 was designed to be more resistant to brute-force and known-plaintext attacks than a single-cipher approach. It mixes two symmetric techniques — bitwise rotation and XOR — to increase **confusion and diffusion** in the ciphertext, and uses **SHA-256** to detect any tampering with the original message.

## How It Works

**Key Generation**
The user provides a secret key, which is converted to bytes. If the key is shorter than the message, it repeats cyclically so every plaintext byte has a corresponding key byte.

**Encryption**
Each plaintext byte is:
1. Rotated right by a fixed number of bits (increases diffusion)
2. XORed with the corresponding key byte (increases confusion)
3. For bytes at even positions, steps 1–2 repeat multiple times based on the key value, adding extra encryption rounds

A SHA-256 hash of the original plaintext is generated alongside the ciphertext, to be used later for integrity verification.

**Decryption**
The process reverses in exact opposite order — XOR first, then rotate left — recovering the original bytes. The recovered plaintext is re-hashed with SHA-256 and compared against the original hash. A match confirms the message wasn't altered in transit.

## Architecture

<img width="1304" height="854" alt="image" src="https://github.com/user-attachments/assets/b4e83198-b3eb-452e-85e7-045af60fab5a" />

<img width="1104" height="1404" alt="image" src="https://github.com/user-attachments/assets/6dfa3a8a-e8a3-4796-92e5-9402517217fc" />



## Tech Stack

- **Java** — core implementation
- **SHA-256** (via `java.security.MessageDigest`) — integrity verification
- Custom bitwise operations for rotation and XOR

## Example Run

```
Enter the message to encrypt (your name): Reem
Enter the encryption key: 5
Binary message: 01010010011001010110010101101101
Binary key: 00110101
Cipher binary: 1111100100110011000101110011000
SHA-256 hash: b3a57bf059f21dae0ab978b05286073aa03ceada1e1ee3dc1784c3e04897f1e3

Enter the cipher to decrypt (binary): [ciphertext]
Enter the decryption key: 5
Enter the original SHA-256 hash (optional): [hash]
Correct cipher
Decrypted message: Reem
```

## What This Project Demonstrates

- Applying core cryptographic principles (confusion, diffusion, integrity) in a real implementation rather than just theory
- Designing a custom algorithm rather than relying solely on a library implementation
- Defending against brute-force and known-plaintext attack scenarios
- Working with byte-level and bit-level operations in Java

## References

- Stallings, W. (2017). *Cryptography and Network Security: Principles and Practice* (7th ed.). Pearson.
- Paar, C., & Pelzl, J. (2010). *Understanding Cryptography: A Textbook for Students and Practitioners*. Springer.
- Katz, J., & Lindell, Y. (2020). *Introduction to Modern Cryptography* (3rd ed.). CRC Press.
- Oracle (2024). *Java Cryptography Architecture (JCA) Reference Guide*.




