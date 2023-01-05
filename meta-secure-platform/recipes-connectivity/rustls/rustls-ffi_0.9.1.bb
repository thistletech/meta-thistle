SUMMARY = "TLS library written in Rust with C bindings"
DESCRIPTION = "Rustls is a TLS library that aims to provide a good level of \
cryptographic security, requires no configuration to achieve that security, and \
provides no unsafe features or obsolete cryptography."
HOMEPAGE = "https://github.com/rustls/rustls"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=1836efb2eb779966696f473ee8540542"
SRC_URI = "git://github.com/rustls/rustls-ffi.git;protocol=https;nobranch=1"
SRC_URI += "crate://crates.io/ansi_term/0.12.1 \
           crate://crates.io/atty/0.2.14 \
           crate://crates.io/autocfg/1.1.0 \
           crate://crates.io/base64/0.13.0 \
           crate://crates.io/bitflags/1.3.2 \
           crate://crates.io/bumpalo/3.11.0 \
           crate://crates.io/cbindgen/0.19.0 \
           crate://crates.io/cc/1.0.73 \
           crate://crates.io/cfg-if/1.0.0 \
           crate://crates.io/clap/2.34.0 \
           crate://crates.io/fastrand/1.8.0 \
           crate://crates.io/hashbrown/0.12.3 \
           crate://crates.io/heck/0.3.3 \
           crate://crates.io/hermit-abi/0.1.19 \
           crate://crates.io/indexmap/1.9.1 \
           crate://crates.io/instant/0.1.12 \
           crate://crates.io/itoa/1.0.3 \
           crate://crates.io/js-sys/0.3.59 \
           crate://crates.io/libc/0.2.132 \
           crate://crates.io/log/0.4.17 \
           crate://crates.io/num_enum/0.5.7 \
           crate://crates.io/num_enum_derive/0.5.7 \
           crate://crates.io/once_cell/1.13.1 \
           crate://crates.io/proc-macro-crate/1.2.1 \
           crate://crates.io/proc-macro2/1.0.43 \
           crate://crates.io/quote/1.0.21 \
           crate://crates.io/redox_syscall/0.2.16 \
           crate://crates.io/remove_dir_all/0.5.3 \
           crate://crates.io/ring/0.16.20 \
           crate://crates.io/rustls-pemfile/0.2.1 \
           crate://crates.io/rustls/0.20.4 \
           crate://crates.io/rustversion/1.0.9 \
           crate://crates.io/ryu/1.0.11 \
           crate://crates.io/sct/0.7.0 \
           crate://crates.io/serde/1.0.144 \
           crate://crates.io/serde_derive/1.0.144 \
           crate://crates.io/serde_json/1.0.85 \
           crate://crates.io/spin/0.5.2 \
           crate://crates.io/strsim/0.8.0 \
           crate://crates.io/syn/1.0.99 \
           crate://crates.io/tempfile/3.3.0 \
           crate://crates.io/textwrap/0.11.0 \
           crate://crates.io/thiserror-impl/1.0.32 \
           crate://crates.io/thiserror/1.0.32 \
           crate://crates.io/toml/0.5.9 \
           crate://crates.io/unicode-ident/1.0.3 \
           crate://crates.io/unicode-segmentation/1.9.0 \
           crate://crates.io/unicode-width/0.1.9 \
           crate://crates.io/untrusted/0.7.1 \
           crate://crates.io/vec_map/0.8.2 \
           crate://crates.io/wasm-bindgen-backend/0.2.82 \
           crate://crates.io/wasm-bindgen-macro-support/0.2.82 \
           crate://crates.io/wasm-bindgen-macro/0.2.82 \
           crate://crates.io/wasm-bindgen-shared/0.2.82 \
           crate://crates.io/wasm-bindgen/0.2.82 \
           crate://crates.io/web-sys/0.3.59 \
           crate://crates.io/webpki/0.22.0 \
           crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
           crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
           crate://crates.io/winapi/0.3.9 \
           file://0001-Generate-a-dynamic-library.patch \
           "

SRCREV = "3e66fba148a8a97935b49c178ccfd1a71929bf2a"
S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"

CARGO_SRC_DIR = ""

# This is a workaround for an issue related to the ELF tag SONAME. Yocto uses
# this flag when linking packages containing libraries. By default rustc does
# not set this tag. This workaround has the downside of setting this flag in all
# internal dependencies but this doesn't really have a negative impact for the
# Yocto use case.
RUSTFLAGS = "-Clink-arg=-Wl,-soname=librustls.so.0"
inherit cargo pkgconfig

# The install step needs to do some hacky magic to handle the fact that this is
# a FFI library since the current rust classes don't explicitily handle this
# case
do_install() {
        libname=librustls.so.0.9.1
        install ${B}/target/${CARGO_TARGET_SUBDIR}/librustls_ffi.so ${B}/target/${CARGO_TARGET_SUBDIR}/$libname
        install -d ${D}${libdir}

        install -m 755 ${B}/target/${CARGO_TARGET_SUBDIR}/$libname ${D}${libdir}
	ln -sf $libname ${D}${libdir}/librustls.so.0
        ln -sf $libname ${D}${libdir}/librustls.so
        install -d ${D}${includedir}
        install ${S}/src/rustls.h ${D}${includedir}
}

PROVIDES += "rustls"
RPROVIDES:rustls-ffi += "rustls"
