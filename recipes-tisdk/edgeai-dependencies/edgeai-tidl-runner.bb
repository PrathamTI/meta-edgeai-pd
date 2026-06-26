SUMMARY = "EdgeAI TIDL Python Dependencies and Artifacts"
DESCRIPTION = "Python dependencies and artifacts for EdgeAI TIDL applications including scipy, librosa, and mobilenet artifacts"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${UNPACKDIR}/src"
PR:append = "_edgeai_1"

SRC_URI = "https://files.pythonhosted.org/packages/69/59/b6fc2188dfc7ea4f936cd12b49d707f66a1cb7a1d2c16172963534db741b/flit_core-3.12.0.tar.gz;name=flit_core;subdir=${S}/deps\
           https://files.pythonhosted.org/packages/22/de/48c59722572767841493b26183a0d1cc411d54fd759c5607c4590b6563a6/tomli-2.4.1.tar.gz;name=tomli;subdir=${S}/deps\
           https://files.pythonhosted.org/packages/ef/f2/7cdb8eb308a1a6ae1e19f945913c82c23c0c442a462a46480ce487fdc0ac/scipy-1.17.1-cp314-cp314-manylinux_2_27_aarch64.manylinux_2_28_aarch64.whl;name=scipy;subdir=${S}/scipy\
           https://files.pythonhosted.org/packages/fd/41/6a3d0ef908f47d07c31e5d1c2504388c27c39b10b8cf610175b5a789a5c1/munkres-1.1.4.tar.gz;name=munkres;subdir=${S}/deps\
           https://files.pythonhosted.org/packages/00/15/a4983fd782472b7968b4a0b50092981debccdd938ba4d94698bbb71b0abb/json_tricks-3.17.3.tar.gz;name=json_tricks;subdir=${S}/deps\
           https://files.pythonhosted.org/packages/b5/ba/c63c5786dfee4c3417094c4b00966e61e4a63efecee22cb7b4c0387dda83/librosa-0.11.0-py3-none-any.whl;name=librosa;subdir=${S}/librosa\
           https://files.pythonhosted.org/packages/b7/d0/2e912d5e1fa017e10c196609d008ff5954ddb9d0487de77a5de03141613a/plyfile-1.1.4-py3-none-any.whl;name=plyfile;subdir=${S}/plyfile\
           https://files.pythonhosted.org/packages/33/90/623f99c55c7d0727a58eb2b7dfb65cb406c561a5c2e9a95b0d6a450c473d/wurlitzer-3.1.1.tar.gz;name=wurlitzer;subdir=${S}/deps\
           https://files.pythonhosted.org/packages/8a/a1/8d812e53a5da1687abb10445275d41a8b13adb781bbf7196ddbcf8d88505/lazy_loader-0.5-py3-none-any.whl;name=lazy_loader;subdir=${S}/lazy_loader\
           https://files.pythonhosted.org/packages/fe/ed/1ce51c70fa3fed5eb332354607052c785a83d43a52ef362c31658a977940/pdm-2.27.0-py3-none-any.whl;name=pdm;subdir=${S}/pdm\
           http://swubn04.india.englab.ti.com/temp-62D/artifacts_mobilenet_v2_tv-onnx.tar.gz;name=artifacts\
           "

SRC_URI[flit_core.sha256sum] = "18f63100d6f94385c6ed57a72073443e1a71a4acb4339491615d0f16d6ff01b2"
SRC_URI[tomli.sha256sum] = "7c7e1a961a0b2f2472c1ac5b69affa0ae1132c39adcb67aba98568702b9cc23f"
SRC_URI[scipy.sha256sum] = "adb2642e060a6549c343603a3851ba76ef0b74cc8c079a9a58121c7ec9fe2350"
SRC_URI[munkres.sha256sum] = "fc44bf3c3979dada4b6b633ddeeb8ffbe8388ee9409e4d4e8310c2da1792db03"
SRC_URI[json_tricks.sha256sum] = "71561eedad7c22dde019e9a38ff8c46ebd91da789e31e2513f627dd2cbbdbf56"
SRC_URI[librosa.sha256sum] = "0b6415c4fd68bff4c29288abe67c6d80b587e0e1e2cfb0aad23e4559504a7fa1"
SRC_URI[plyfile.sha256sum] = "69eb9bfa85de9396e516e89d5b87abc8640e78b5dddd9df25926fb33df314edb"
SRC_URI[wurlitzer.sha256sum] = "bfb9144ab9f02487d802b9ff89dbd3fa382d08f73e12db8adc4c2fb00cd39bd9"
SRC_URI[lazy_loader.sha256sum] = "ab0ea149e9c554d4ffeeb21105ac60bed7f3b4fd69b1d2360a4add51b170b005"
SRC_URI[pdm.sha256sum] = "2f56c4a8f01809564430fb35e850c0b9f56f45e71f272b35dece4db0d558a3aa"
SRC_URI[artifacts.sha256sum] = "a2de90ad68fe08ac88e2b0c4a5d0f9dad82b6e96a01ae906121e631c22bf31ab"


DEPENDS += "unzip-native python3-setuptools-native"

RDEPENDS:${PN} += " \
     python3-mldtypes \
     python3-decorator \
     python3-graphviz \
     python3-attrs \
     python3-psutil \
     python3-typing-extensions \
     python3-h5py \
     python3-openpyxl \
     python3-pycocotools \
     python3-tabulate \
     python3-tqdm \
     python3-xlsxwriter \
     python3-colorama \
     python3-cython \
     python3-pyyaml \
     python3-beautifulsoup4 \
     bash \
"

# Skip QA checks for Python packages, DSP binaries, and pre-compiled wheels
INSANE_SKIP:${PN} += "already-stripped"
# Skip architecture check for DSP binaries in artifacts (C7x cores have different architecture)
INSANE_SKIP:${PN} += "arch"
# Skip GNU_HASH check for DSP binaries in artifacts (they don't use standard ELF linking)
INSANE_SKIP:${PN} += "ldflags"
# Skip buildpaths check for pip-installed packages that contain direct_url.json with build paths
INSANE_SKIP:${PN} += "buildpaths"
# Skip text relocations check for DSP binaries (C7x binaries may have relocations in .text)
INSANE_SKIP:${PN} += "textrel"
# Skip file-rdeps check for scipy which requires Fortran runtime libraries
INSANE_SKIP:${PN} += "file-rdeps"
# Skip shared library conflicts for PyTorch bundled libraries (libgomp.so.1)
INSANE_SKIP:${PN} += "shlib-without-dep"
# Skip multiple shared library provider conflicts for PyTorch (libgomp.so.1)
INSANE_SKIP:${PN} += "shlib"
# Skip multiple providers check for specific shared libraries
INSANE_SKIP:${PN} += "multiple-shlib-providers"

COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|j722s|j742s2|am62axx|am62dxx"

inherit python3-dir

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/*"
FILES:${PN} += "/root/*"


do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}

    # Install wheel packages directly
    unzip -d ${D}${PYTHON_SITEPACKAGES_DIR} ${S}/lazy_loader/lazy_loader-0.5-py3-none-any.whl
    unzip -d ${D}${PYTHON_SITEPACKAGES_DIR} ${S}/pdm/pdm-2.27.0-py3-none-any.whl
    unzip -d ${D}${PYTHON_SITEPACKAGES_DIR} ${S}/scipy/scipy-1.17.1-cp314-cp314-manylinux_2_27_aarch64.manylinux_2_28_aarch64.whl
    unzip -d ${D}${PYTHON_SITEPACKAGES_DIR} ${S}/librosa/librosa-0.11.0-py3-none-any.whl
    unzip -d ${D}${PYTHON_SITEPACKAGES_DIR} ${S}/plyfile/plyfile-1.1.4-py3-none-any.whl

    # Define package directories for installation
    BUILD_DEPS="flit_core-3.12.0 tomli-2.4.1"
    ALL_DEPS="flit_core-3.12.0 tomli-2.4.1 munkres-1.1.4 json_tricks-3.17.3 wurlitzer-3.1.1"

    # First install build dependencies to current environment (required for building source packages)
    for pkg in ${BUILD_DEPS}; do
        bbnote "Installing build dependency: $pkg to current environment"
        cd ${S}/deps/$pkg
        python3 -m pip install . --no-deps --no-build-isolation
    done

    # Now install all source packages to target directory
    for pkg in ${ALL_DEPS}; do
        bbnote "Installing package: $pkg to target directory"
        cd ${S}/deps/$pkg
        python3 -m pip install . --target ${D}${PYTHON_SITEPACKAGES_DIR} --no-deps --no-build-isolation
    done

    # Return to original working directory
    cd ${S}

    # Install artifacts folder to /root
    install -d ${D}/root
    cp -r ${UNPACKDIR}/artifacts_mobilenet_v2_tv-onnx ${D}/root/
}