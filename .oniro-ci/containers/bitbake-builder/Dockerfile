# Copyright 2021 Huawei Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# SPDX-License-Identifier: Apache-2.0

FROM ubuntu:20.04

COPY --chown=root:root ppa/zyga-ubuntu-oh-tools-focal.list /etc/apt/sources.list.d/
COPY --chown=root:root ppa/zyga-ubuntu-oh-tools.gpg /etc/apt/trusted.gpg.d/

ARG DEBIAN_FRONTEND="noninteractive"
RUN apt-get update -qq \
 && apt-get install -y eatmydata \
 && eatmydata apt-get install -qq -y \
	bash git-repo git-lfs apt-utils build-essential chrpath cpio diffstat \
	gawk git sudo wget language-pack-en-base time locales python-is-python3 \
	python3-distutils python3-pip libssl-dev iproute2 iputils-ping curl jq \
	lz4 zstd iptables\
 && eatmydata apt-get install -qq -y 'ca-certificates=20211016~20.04.1' \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*
RUN locale-gen
RUN pip3 install anybadge

# Let's just have /bin/sh as bash
RUN echo "dash dash/sh boolean false" | debconf-set-selections \
 && DEBIAN_FRONTEND=noninteractive dpkg-reconfigure dash

# runqemu command needs sudo permission to create tun device for the
# communication between host and vm for gcc testing.
RUN useradd --create-home --uid 1000 --shell /usr/bin/bash builder \
 && usermod -aG sudo builder\
 && echo "builder ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers

COPY --chown=builder:builder .gitconfig /home/builder/.gitconfig
USER builder
WORKDIR /home/builder
