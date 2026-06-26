require recipes-core/images/tisdk-default-image.bb

# Produces wic Image for Edge AI demos
SUMMARY = "Arago based TI SDK full filesystem image intended for EdgeAI"

DESCRIPTION = "Complete Arago TI SDK filesystem image containing entire \
 edgeAI stack intended for TI Analytics processors."

COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|j722s|am62axx|am62dxx"

EDGEAI_STACK = " \
        ti-tidl-osrt-dev \
        ti-tidl-osrt-staticdev \
        edgeai-tidl-runner \
"

#EDGEAI_STACK:remove:am62axx = "ti-edgeai-firmware"

IMAGE_INSTALL += " \
    ${EDGEAI_STACK} \
    resize-rootfs \
    packagegroup-arago-gst-sdk-target \
    packagegroup-edgeai-tisdk-addons \
    python3-soundfile \
"

export IMAGE_BASENAME = "tisdk-edgeai-image${ARAGO_IMAGE_SUFFIX}"

PR:append = "_edgeai_8"
