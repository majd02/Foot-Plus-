<?php

namespace App\Controller;

use App\Entity\Feedback;
use App\Entity\Reclamation;
use App\Form\FeedbackType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/feedback")
 */
class FeedbackController extends AbstractController
{
    /**
     * @Route("/", name="feedback_index", methods={"GET"})
     */
    public function index(): Response
    {
        $feedback = $this->getDoctrine()
            ->getRepository(Feedback::class)
            ->findAll();

        return $this->render('feedback/index.html.twig', [
            'feedback' => $feedback,
        ]);
    }
    /**
     * @Route("/front", name="feedbackf_index", methods={"GET"})
     */
    public function index2(): Response
    {
        $feedback = $this->getDoctrine()
            ->getRepository(Feedback::class)
            ->findAll();

        return $this->render('feedbackf/index.html.twig', [
            'feedback' => $feedback,
        ]);
    }

    /**
     * @Route("/front/new", name="feedbackf_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $feedback = new Feedback();
        $form = $this->createForm(FeedbackType::class, $feedback);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($feedback);
            $entityManager->flush();

            return $this->redirectToRoute('feedbackf_index');
        }

        return $this->render('feedbackf/new.html.twig', [
            'feedback' => $feedback,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/front/{id}", name="feedbackf_show", methods={"GET"})
     */
    public function show(Feedback $feedback): Response
    {
        return $this->render('feedbackf/show.html.twig', [
            'feedback' => $feedback,
        ]);
    } /**
 * @Route("/{id}", name="feedback_show", methods={"GET"})
 */
    public function show2(Feedback $feedback): Response
    {
        return $this->render('feedback/show.html.twig', [
            'feedback' => $feedback,
        ]);
    }

    /**
     * @Route("/front/{id}/edit", name="feedbackf_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Feedback $feedback): Response
    {
        $form = $this->createForm(FeedbackType::class, $feedback);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('feedbackf_index');
        }

        return $this->render('feedbackf/edit.html.twig', [
            'feedback' => $feedback,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/front/{id}", name="feedbackf_delete", methods={"POST"})
     */
    public function delete(Request $request, Feedback $feedback): Response
    {
        if ($this->isCsrfTokenValid('delete'.$feedback->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($feedback);
            $entityManager->flush();
        }

        return $this->redirectToRoute('feedbackf_index');
    }
    /**
     * @Route("/TrierParRateDESC", name="TrierParRateDESC")
     */
    public function TrierParRate(Request $request): Response
    {
        $repository = $this->getDoctrine()->getRepository(Feedback::class);
        $feedback = $repository->findByRate();

        return $this->render('feedback/index.html.twig', [
            'feedback' => $feedback,
        ]);
    }
    /**
     * @Route("/{id}", name="feedback_delete", methods={"POST"})
     */
    public function delete2(Request $request, Feedback $feedback): Response
    {
        if ($this->isCsrfTokenValid('delete'.$feedback->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($feedback);
            $entityManager->flush();
        }

        return $this->redirectToRoute('feedback_index');
    }
}
