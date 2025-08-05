package com.pdfnlp.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class SamplePDFGenerator {
    private static final Logger logger = LoggerFactory.getLogger(SamplePDFGenerator.class);
    
    public static void generateSamplePDF(String outputPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            
            // Set fonts (using built-in fonts for compatibility)
            Font koreanFont = new Font(Font.FontFamily.HELVETICA, 12);
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            
            // Page 1: Introduction to Virtual Reality
            document.add(new Paragraph("가상현실 기술의 개요", titleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실(Virtual Reality, VR)은 컴퓨터로 생성된 환경에서 사용자가 실제와 같은 경험을 할 수 있도록 하는 기술입니다. 이 기술은 현실과 구분하기 어려운 가상의 세계를 만들어내어 사용자에게 몰입감을 제공합니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실의 주요 특징:", subtitleFont));
            document.add(new Paragraph("• 몰입감: 사용자가 가상 환경에 완전히 몰입할 수 있음", koreanFont));
            document.add(new Paragraph("• 상호작용: 사용자의 움직임과 행동에 반응하는 환경", koreanFont));
            document.add(new Paragraph("• 3차원 공간: 입체적인 공간감을 제공하는 환경", koreanFont));
            document.add(new Paragraph("• 실시간 렌더링: 사용자의 움직임에 따라 실시간으로 변화하는 화면", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실 기술은 게임, 교육, 의료, 건축, 군사 훈련 등 다양한 분야에서 활용되고 있습니다. 특히 게임 산업에서는 몰입형 게임 경험을 제공하는 핵심 기술로 자리잡고 있습니다.", koreanFont));
            
            document.newPage();
            
            // Page 2: VR Hardware and Components
            document.add(new Paragraph("가상현실 하드웨어 및 구성 요소", titleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실 시스템의 주요 하드웨어 구성 요소:", subtitleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("1. 헤드마운트 디스플레이(HMD)", subtitleFont));
            document.add(new Paragraph("가상현실 헤드셋은 사용자의 눈 앞에 가상 세계를 표시하는 디스플레이 장치입니다. 최신 VR 헤드셋은 고해상도 OLED 또는 LCD 패널을 사용하여 선명한 화질을 제공합니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("2. 모션 트래킹 시스템", subtitleFont));
            document.add(new Paragraph("사용자의 머리와 몸의 움직임을 감지하여 가상 환경에서의 위치와 방향을 결정합니다. 자이로스코프, 가속도계, 외부 센서 등을 사용합니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("3. 컨트롤러", subtitleFont));
            document.add(new Paragraph("가상 환경에서의 상호작용을 위한 입력 장치입니다. 버튼, 트리거, 터치패드 등을 포함하며, 사용자의 손 움직임을 감지합니다.", koreanFont));
            
            document.newPage();
            
            // Page 3: Artificial Intelligence
            document.add(new Paragraph("인공지능과 가상현실의 융합", titleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("인공지능(AI) 기술이 가상현실에 적용되면서 더욱 지능적이고 자연스러운 가상 환경이 만들어지고 있습니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("AI가 VR에 미치는 영향:", subtitleFont));
            document.add(new Paragraph("• 지능형 NPC: AI가 제어하는 가상 캐릭터들이 더욱 자연스럽고 지능적으로 행동", koreanFont));
            document.add(new Paragraph("• 적응형 환경: 사용자의 행동 패턴을 학습하여 개인화된 경험 제공", koreanFont));
            document.add(new Paragraph("• 자연어 처리: 음성 명령과 대화를 통한 직관적인 상호작용", koreanFont));
            document.add(new Paragraph("• 컴퓨터 비전: 사용자의 표정과 감정을 인식하여 반응하는 시스템", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("머신러닝 알고리즘을 활용한 VR 시스템은 사용자의 선호도와 행동을 지속적으로 학습하여 더욱 개인화된 경험을 제공할 수 있습니다.", koreanFont));
            
            document.newPage();
            
            // Page 4: Applications and Use Cases
            document.add(new Paragraph("가상현실의 응용 분야", titleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실 기술의 주요 응용 분야:", subtitleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("1. 게임 및 엔터테인먼트", subtitleFont));
            document.add(new Paragraph("가장 널리 알려진 VR 응용 분야로, 몰입형 게임 경험을 제공합니다. 사용자는 가상 세계에서 자유롭게 탐험하고 상호작용할 수 있습니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("2. 교육 및 훈련", subtitleFont));
            document.add(new Paragraph("위험하거나 비용이 많이 드는 상황을 안전하게 시뮬레이션할 수 있습니다. 의료 수술 훈련, 비행 조종사 훈련, 군사 훈련 등에 활용됩니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("3. 의료 및 치료", subtitleFont));
            document.add(new Paragraph("공포증 치료, 통증 관리, 재활 치료 등에 사용됩니다. 가상 환경에서 환자가 안전하게 치료를 받을 수 있습니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("4. 건축 및 설계", subtitleFont));
            document.add(new Paragraph("건물이나 제품의 3D 모델을 가상 환경에서 미리 체험할 수 있어 설계 과정에서의 의사결정을 돕습니다.", koreanFont));
            
            document.newPage();
            
            // Page 5: Future Trends and Challenges
            document.add(new Paragraph("가상현실의 미래 동향과 과제", titleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실 기술의 미래 발전 방향:", subtitleFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("1. 향상된 해상도와 화질", subtitleFont));
            document.add(new Paragraph("8K 이상의 고해상도 디스플레이와 HDR 기술을 통해 더욱 선명하고 생생한 가상 환경을 제공할 것입니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("2. 무선 기술의 발전", subtitleFont));
            document.add(new Paragraph("케이블 없이도 고품질의 VR 경험을 제공하는 무선 기술이 발전하고 있습니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("3. 증강현실(AR)과의 융합", subtitleFont));
            document.add(new Paragraph("VR과 AR이 결합된 혼합현실(MR) 기술이 발전하여 현실과 가상의 경계를 더욱 모호하게 만들 것입니다.", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("주요 과제:", subtitleFont));
            document.add(new Paragraph("• 모션 시퀄니티: 장시간 사용 시 발생할 수 있는 어지럼증과 메스꺼움", koreanFont));
            document.add(new Paragraph("• 사회적 고립: 가상 세계에 몰입하여 현실 세계와의 단절", koreanFont));
            document.add(new Paragraph("• 개인정보 보호: VR 환경에서 수집되는 개인 데이터의 보안", koreanFont));
            document.add(new Paragraph("• 접근성: 장애인을 포함한 모든 사용자가 이용할 수 있는 보편적 설계", koreanFont));
            document.add(new Paragraph(" ", koreanFont));
            
            document.add(new Paragraph("가상현실 기술은 지속적으로 발전하고 있으며, 앞으로 더욱 많은 분야에서 활용될 것으로 예상됩니다. 기술의 발전과 함께 윤리적, 사회적 측면에서의 고려도 중요해질 것입니다.", koreanFont));
            
            document.close();
            logger.info("Sample PDF generated successfully: {}", outputPath);
            
        } catch (DocumentException e) {
            logger.error("Error generating sample PDF", e);
            throw new RuntimeException("Failed to generate sample PDF", e);
        } catch (IOException e) {
            logger.error("Error generating sample PDF", e);
            throw new RuntimeException("Failed to generate sample PDF", e);
        }
    }
    
    public static void main(String[] args) {
        generateSamplePDF("sample_korean_tech.pdf");
        System.out.println("Sample PDF 'sample_korean_tech.pdf' has been generated successfully!");
        System.out.println("You can now use this file to test the PDF search functionality.");
    }
} 